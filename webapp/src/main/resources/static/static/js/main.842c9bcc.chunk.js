(this.webpackJsonpspa=this.webpackJsonpspa||[]).push([[0],{25:function(e,t,s){},26:function(e,t,s){},38:function(e,t,s){"use strict";s.r(t);var a=s(2),n=s.n(a),r=s(9),c=s.n(r),i=(s(25),s(26),s(17)),l=s(18),u=s(10),o=s(20),d=s(19),j=s(50),h=s(40),b=s(41),O=s(42),v=s(43),m=s(44),x=s(45),p=s(46),f=s(47),S=s(48),N=s(49),g="numeric",y=s(3),P=function(e){Object(o.a)(s,e);var t=Object(d.a)(s);function s(e){var a;return Object(i.a)(this,s),(a=t.call(this,e)).state={results:null,isProcessing:!1},a.onSubmit=a.onSubmit.bind(Object(u.a)(a)),a.testNameInput=n.a.createRef(),a.testValueInput=n.a.createRef(),a}return Object(l.a)(s,[{key:"onSubmit",value:function(e){e.preventDefault();var t={type:g,testName:this.testNameInput.current.value,value:this.testValueInput.current.value};this.setState({isProcessing:!0}),this.sendRequest(t)}},{key:"sendRequest",value:function(e){var t=this,s="An error occured, failed to evaluate result.";fetch("/api/evaluateSingleTest",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify(e)}).then((function(e){t.setState({isProcessing:!1}),e.ok&&"200"==e.status?e.json().then((function(e){t.setState({results:{error:null,data:e}})})).catch((function(e){console.warn(e),t.setState({results:{error:s,data:null}})})):(console.warn("Failed to fetch, HTTP status: ".concat(e.status)),t.setState({results:{error:s,data:null}}))})).catch((function(e){console.warn(e),t.setState({isProcessing:!1,results:{error:s,data:null}})}))}},{key:"formatResults",value:function(e){return e.error?Object(y.jsx)(j.a,{color:"danger",children:e.error}):!0===e.data.matchSuccess?Object(y.jsxs)(j.a,{color:"success",children:[e.data.officialName," : ",e.data.evaluationResult]}):Object(y.jsx)(j.a,{color:"warning",children:e.data.evaluationResult})}},{key:"render",value:function(){return Object(y.jsxs)(h.a,{children:[Object(y.jsx)(b.a,{children:"Blood Test Results"}),Object(y.jsx)(O.a,{children:"Please enter your blood test result name and value."}),Object(y.jsx)(v.a,{children:Object(y.jsxs)("div",{children:[Object(y.jsx)("div",{children:Object(y.jsx)(m.a,{onSubmit:this.onSubmit,children:Object(y.jsxs)("div",{className:"inputs-container",children:[Object(y.jsxs)("div",{children:[Object(y.jsxs)("div",{className:"textbox-input",children:[Object(y.jsx)(x.a,{for:"test-name",children:"Test Name:"}),Object(y.jsx)(p.a,{readOnly:this.state.isProcessing,required:!0,id:"test-name",type:"text",innerRef:this.testNameInput}),Object(y.jsx)(f.a,{children:"Please provide a name"})]}),Object(y.jsxs)("div",{className:"textbox-input",children:[Object(y.jsx)(x.a,{for:"test-value",children:"Test Result Value:"}),Object(y.jsx)(p.a,{readOnly:this.state.isProcessing,required:!0,id:"test-value",type:"number",innerRef:this.testValueInput}),Object(y.jsx)(f.a,{children:"Please provide a value"})]})]}),Object(y.jsxs)("div",{className:"submit-button",children:[Object(y.jsx)(S.a,{className:"submit-button-override",disabled:this.state.isProcessing,children:"EVALUATE"}),Object(y.jsx)("div",{className:"processing-spinner ".concat(this.state.isProcessing?"":"hidden"),children:Object(y.jsx)(N.a,{})})]})]})})}),Object(y.jsx)("div",{className:"results-container ".concat(null==this.state.results?"hidden":""),children:this.state.results&&this.formatResults(this.state.results)})]})})]})}}]),s}(n.a.Component);var R=function(){return Object(y.jsx)("div",{className:"app",children:Object(y.jsx)(P,{})})};s(37);c.a.render(Object(y.jsx)(n.a.StrictMode,{children:Object(y.jsx)(R,{})}),document.getElementById("root"))}},[[38,1,2]]]);
//# sourceMappingURL=main.842c9bcc.chunk.js.map