(this.webpackJsonpspa=this.webpackJsonpspa||[]).push([[0],{27:function(e,t,a){},28:function(e,t,a){},40:function(e,t,a){"use strict";a.r(t);var s=a(2),n=a.n(s),i=a(9),r=a.n(i),l=(a(27),a(28),a(10)),c=a(12),o=a(19),u=a(20),d=a(11),h=a(22),j=a(21),v=a(52),b=a(42),m=a(43),f=a(44),O=a(45),p=a(46),x=a(47),y=a(48),N=a(49),S=a(50),V=a(51),g="numeric",R="Good",P="Bad",k="Unknown",w=a(3),T=function(e){Object(h.a)(a,e);var t=Object(j.a)(a);function a(e){var s;return Object(o.a)(this,a),(s=t.call(this,e)).state={results:null,isProcessing:!1,formValidity:{testName:!0,testValue:!0}},s.onSubmit=s.onSubmit.bind(Object(d.a)(s)),s.testNameInput=n.a.createRef(),s.testValueInput=n.a.createRef(),s}return Object(u.a)(a,[{key:"onSubmit",value:function(e){e.preventDefault();var t={testName:this.testNameInput.current.value,value:this.testValueInput.current.value},a=this.validateForm(t);if(!0!==a.valid)this.setState({formValidity:a.validationValues});else{var s=Object(c.a)({type:g},t);this.setState({isProcessing:!0}),this.sendRequest(s)}}},{key:"validateForm",value:function(e){var t=!0,a=!0;return e.testName&&e.testName.length>0||(t=!1),null!==e.value&&void 0!==e.value&&e.value.length>1&&!Number.isNaN(e.value)||(a=!1),{valid:t&&a,validationValues:{testName:t,testValue:a}}}},{key:"sendRequest",value:function(e){var t=this,a="An error occured, failed to evaluate result.";fetch("/api/evaluateSingleTest",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(e)}).then((function(e){t.setState({isProcessing:!1}),e.ok&&"200"==e.status?e.json().then((function(e){t.setState({results:{error:null,data:e}})})).catch((function(e){console.warn(e),t.setState({results:{error:a,data:null}})})):(console.warn("Failed to fetch, HTTP status: ".concat(e.status)),t.setState({results:{error:a,data:null}}))})).catch((function(e){console.warn(e),t.setState({isProcessing:!1,results:{error:a,data:null}})}))}},{key:"validateResponse",value:function(e){return!!e.hasOwnProperty("matchSuccess")&&(!0===e.matchSuccess?e.hasOwnProperty("officialName")&&e.officialName&&e.hasOwnProperty("evaluationResult")&&e.evaluationResult:e.hasOwnProperty("evaluationResult")&&e.evaluationResult)}},{key:"resetValidationStatus",value:function(e){this.setState({formValidity:Object(c.a)(Object(c.a)({},this.state.formValidity),{},Object(l.a)({},e,!0))})}},{key:"formatResults",value:function(e){if(e.error)return Object(w.jsx)(v.a,{color:"danger",children:e.error});if(this.validateResponse(e.data)){var t;switch(e.data.evaluationResult){case R:t="success";break;case P:t="warning";break;case k:default:t="info"}return!0===e.data.matchSuccess?Object(w.jsxs)(v.a,{color:t,children:[e.data.officialName,": ",e.data.evaluationResult]}):Object(w.jsx)(v.a,{color:t,children:e.data.evaluationResult})}return console.warn(e.data),Object(w.jsx)(v.a,{color:"danger",children:"An error occured, data could not be formatted properly."})}},{key:"render",value:function(){var e=this;return Object(w.jsxs)(b.a,{children:[Object(w.jsx)(m.a,{children:"Blood Test Results"}),Object(w.jsx)(f.a,{children:"Please enter your blood test result name and value."}),Object(w.jsx)(O.a,{children:Object(w.jsxs)("div",{children:[Object(w.jsx)("div",{children:Object(w.jsx)(p.a,{onSubmit:this.onSubmit,children:Object(w.jsxs)("div",{className:"inputs-container",children:[Object(w.jsxs)("div",{children:[Object(w.jsxs)("div",{className:"textbox-input",children:[Object(w.jsx)(x.a,{for:"test-name",children:"Test Name:"}),Object(w.jsx)(y.a,{onChange:function(){e.state.formValidity.testName||e.resetValidationStatus("testName")},readOnly:this.state.isProcessing,invalid:!this.state.formValidity.testName,id:"test-name",type:"text",innerRef:this.testNameInput}),Object(w.jsx)(N.a,{children:"Please provide a valid name"})]}),Object(w.jsxs)("div",{className:"textbox-input",children:[Object(w.jsx)(x.a,{for:"test-value",children:"Test Result Value:"}),Object(w.jsx)(y.a,{onChange:function(){e.state.formValidity.testValue||e.resetValidationStatus("testValue")},readOnly:this.state.isProcessing,invalid:!this.state.formValidity.testValue,id:"test-value",type:"float",innerRef:this.testValueInput}),Object(w.jsx)(N.a,{children:"Please provide a numeric value"})]})]}),Object(w.jsxs)("div",{className:"submit-button",children:[Object(w.jsx)(S.a,{className:"submit-button-override",disabled:this.state.isProcessing,children:"EVALUATE"}),Object(w.jsx)("div",{className:"processing-spinner ".concat(this.state.isProcessing?"":"hidden"),children:Object(w.jsx)(V.a,{})})]})]})})}),Object(w.jsx)("div",{className:"results-container ".concat(null==this.state.results?"hidden":""),children:this.state.results&&this.formatResults(this.state.results)})]})})]})}}]),a}(n.a.Component);var I=function(){return Object(w.jsx)("div",{className:"app",children:Object(w.jsx)(T,{})})};a(39);r.a.render(Object(w.jsx)(n.a.StrictMode,{children:Object(w.jsx)(I,{})}),document.getElementById("root"))}},[[40,1,2]]]);
//# sourceMappingURL=main.25bc2c53.chunk.js.map