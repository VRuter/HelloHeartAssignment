import React from 'react';
import { Card, CardTitle, CardSubtitle, CardBody, Input, Button, Label, Form, FormFeedback, Spinner, Alert } from 'reactstrap';
import { TestTypes, EvaluationResults } from './BloodTestConstants';

export default class BloodTestEvaluator extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      results: null,
      isProcessing: false,
      formValidity: {
        testName: true,
        testValue: true
      }
    }

    this.onSubmit = this.onSubmit.bind(this);

    this.testNameInput = React.createRef();
    this.testValueInput = React.createRef();
  }

  /**
   * Callback for submitting the form. Also handles validation and initiates the backend request.
   */
  onSubmit(event) {
    event.preventDefault();

    let formValues = {
      testName: this.testNameInput.current.value,
      value: this.testValueInput.current.value
    }

    let formValidationResult = this.validateForm(formValues);

    if (formValidationResult.valid !== true) {
      this.setState({ formValidity: formValidationResult.validationValues });
    } else {
      let testResultObject = {
        type: TestTypes.NUMERIC,
        ...formValues
      }

      this.setState({ isProcessing: true });
      this.sendRequest(testResultObject);
    }
  }

  /**
   * Explicitly validates the form inputs and returns the validation result and validation values.
   */
  validateForm(formValues) {
    let testNameValidity = true;
    let testValueValidity = true;

    if (!(formValues.testName && formValues.testName.length > 0)) {
      testNameValidity = false;
    }

    if (!(formValues.value !== null && formValues.value !== undefined && formValues.value.length > 1 && !isNaN(formValues.value))) {
      testValueValidity = false;
    }

    return {
      valid: (testNameValidity && testValueValidity),
      validationValues: {
        testName: testNameValidity,
        testValue: testValueValidity
      }
    }
  }

  /**
   * Sends the backend request for the form inputs. Handles the response and changes component state accordingly.
   */
  sendRequest(requestObject) {
    const errorMessage = "An error occured, failed to evaluate result.";

    fetch("/api/evaluateSingleTest",
      {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(requestObject)
      }
    )
      .then(response => {
        this.setState({ isProcessing: false });

        if (response.ok && response.status == "200") {
          response.json().then(data => {
            this.setState({ results: { error: null, data: data } });
          }).catch(error => {
            console.warn(error);
            this.setState({ results: { error: errorMessage, data: null } });
          });
        } else {
          console.warn(`Failed to fetch, HTTP status: ${response.status}`);
          this.setState({ results: { error: errorMessage, data: null } });
        }
      }).catch(error => {
        console.warn(error);
        this.setState({ isProcessing: false, results: { error: errorMessage, data: null } });
      })
  }

  /**
   * Validates response object structure to make sure it can be parsed properly. Returns boolean.
   */
  validateResponse(responseObj) {
    if (responseObj.hasOwnProperty("matchSuccess")) {
      if (responseObj.matchSuccess === true) {
        return responseObj.hasOwnProperty("officialName") && responseObj.officialName && responseObj.hasOwnProperty("evaluationResult") && responseObj.evaluationResult
      } else {
        return responseObj.hasOwnProperty("evaluationResult") && responseObj.evaluationResult
      }
    }
    return false;
  }

  /**
   * Fires to reset a form field's validation status. Should be called when the field is changed.
   */
  resetValidationStatus(valueName) {
    this.setState({
      formValidity: {
        ...this.state.formValidity,
        [valueName]: true
      }
    });
  }

  /**
   * Returns a JSX object according to the response recieved from the backend.
   */
  formatResults(results) {
    if (results.error) {
      return <Alert color="danger">{results.error}</Alert>
    } else {
      if (this.validateResponse(results.data)) {

        let color;
        switch (results.data.evaluationResult) {
          case EvaluationResults.GOOD:
            color = "success";
            break;
          case EvaluationResults.BAD:
            color = "warning";
            break;
          case EvaluationResults.UNKNOWN:
            color = "info";
            break;
          default:
            color = "info";
            break;
        }

        if (results.data.matchSuccess === true) {
          return <Alert color={color}>{results.data.officialName}: {results.data.evaluationResult}</Alert>
        } else {
          return <Alert color={color}>{results.data.evaluationResult}</Alert>
        }
      } else {
        console.warn(results.data);
        return <Alert color="danger">An error occured, data could not be formatted properly.</Alert>
      }
    }
  }

  render() {
    return (
      <Card>
        <CardTitle>Blood Test Results</CardTitle>
        <CardSubtitle>Please enter your blood test result name and value.</CardSubtitle>
        <CardBody>
          <div>
            <div>
              <Form onSubmit={this.onSubmit}>
                <div className="inputs-container">
                  <div>
                    <div className="textbox-input">
                      <Label for="test-name">Test Name:</Label>
                      <Input onChange={() => { if (!this.state.formValidity.testName) this.resetValidationStatus("testName") }} readOnly={this.state.isProcessing} invalid={!this.state.formValidity.testName} id="test-name" type="text" innerRef={this.testNameInput} />
                      <FormFeedback>Please provide a valid name</FormFeedback>
                    </div>
                    <div className="textbox-input">
                      <Label for="test-value">Test Result Value:</Label>
                      <Input onChange={() => { if (!this.state.formValidity.testValue) this.resetValidationStatus("testValue") }} readOnly={this.state.isProcessing} invalid={!this.state.formValidity.testValue} id="test-value" type="float" innerRef={this.testValueInput} />
                      <FormFeedback>Please provide a numeric value</FormFeedback>
                    </div>
                  </div>
                  <div className="submit-button">
                    <Button className="submit-button-override" disabled={this.state.isProcessing}>EVALUATE</Button>
                    <div className={`processing-spinner ${!this.state.isProcessing ? "hidden" : ""}`}><Spinner /></div>
                  </div>
                </div>
              </Form>
            </div>
            <div className={`results-container ${this.state.results == null ? "hidden" : ""}`}>
              {this.state.results && this.formatResults(this.state.results)}
            </div>
          </div>
        </CardBody>
      </Card>
    )
  }
}