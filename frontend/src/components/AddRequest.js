import Grid  from './Grid'
import StatusCodes from './HttpStatusCodes'
import React, { useState, useEffect, }  from 'react';


const  headers = [
  { key: 'key', name: 'HeaderName', editable: true,  width: '30%' },
  { key: 'value', name: 'HeaderValue', editable: true , width: '50%'},
  { key: 'action', name: 'Action', width: '10%' }
];

const  cookies = [
  { key: 'key', name: 'CookieName', editable: true, width: '30%'   },
  { key: 'value', name: 'CookieValue', editable: true, width: '50%' },
  { key: 'action', name: 'Action', width: '10%'}
];

const  responseVariables = [
     { key: 'key', name: 'JsonPath', editable: true, width: '30%'  },
  { key: 'value', name: 'Expected', editable: true, width: '50%' },
  { key: 'action', name: 'Action',width: '10%' }
];

const  variables = [
  { key: 'key', name: 'VariableName', editable: true, width: '30%'  },
  { key: 'value', name: 'JsonPath', editable: true, width: '50%' },
  { key: 'action', name: 'Action', }
];

const  formvariables = [
  { key: 'key', name: 'Form Params', editable: true, width: '30%'  },
  { key: 'value', name: 'Value', editable: true, width: '50%' },
  { key: 'action', name: 'Action', }
];

const  evaluateFunctions = [
  { key: 'key', name: 'Evalue Variables', editable: true, width: '30%'  },
  { key: 'value', name: 'JsonPath', editable: true, width: '50%' },
  { key: 'action', name: 'Action', }
];

const  addify = [
  { key: 'key', name: 'New Variables', editable: true, width: '30%'  },
  { key: 'value', name: 'value', editable: true, width: '50%' },
  { key: 'action', name: 'Action', }
];


const AddRequest = ({ onAdd, request }) => {
  const [scenarioId, setScenarioId] = useState(request && request.scenarioId?request.scenarioId:'')
  const [type, setType] = useState(request && request.type?request.type:'')
  const [resource, setResource] = useState(request && request.resource?request.resource:'')
  const [scenario, setScenario] = useState(request && request.scenario?request.scenario:'')
  const [url, setUrl] = useState(request && request.url?request.url:'')
  const [method, setMethod] = useState(request && request.method?request.method:'')
  const [contentType, setContentType] = useState(request && request.contentType?request.contentType:'')
  const [httpStatusCode, setHttpStatusCode] = useState(request && request.httpStatusCode?request.httpStatusCode:'')
  const [input, setInput] = useState(request && request.input?request.input:'')
  const [output, setOutput] = useState(request && request.output?request.output:'')
  const [csvson, setCsvson ] = useState(request && request.csvson?request.csvson:'')
  const [stepInfo, setStepInfo ] = useState(request && request.stepInfo?request.stepInfo:'')
  const [cookieVariables, setCookieVariables] = useState(request && request.cookieVariables?request.cookieVariables:[])
  const [storeResponseVariables, setStoreResponseVariables] = useState(request && request.storeResponseVariables?request.storeResponseVariables:[])
  const [outputField, setOutputField] = useState(request && request.outputField?request.outputField:[])
  const [requestHeaders, setRequestHeaders] = useState(request && request.requestHeaders?request.requestHeaders:[])
  const [addifyVariables, setAddifyVariables] = useState(request && request.addifyVariables?request.addifyVariables:[])
  const [evaluateFunctionVariables, setEvaluateFunctionVariables] = useState(request && request.evaluateFunctionVariables?request.evaluateFunctionVariables:[])
  const [formFunctionVariables, setFormFunctionVariables] = useState(request && request.formFunctionVariables?request.formFunctionVariables:[])
  const [refresh, setRefresh] = useState(false);
  const [editRefresh, doEditRefresh] = useState(0);
  const [reload, setReload] = useState(false);


  const onSubmit = (e) => {
    e.preventDefault()

    if (!scenarioId) {
      alert('Please add a request')
      return
    }

    let headers =  {"parameterType": "HEADER_PARAM", "value":contentType,"key":"contentType"} 
    let params = [];
    params.push(headers);
    if(storeResponseVariables.length >0){
        storeResponseVariables.forEach(element => {
        let param  =  {"parameterType": "STORAGE_PARAM", "value":element.value,"key":element.key}
        params.push(param);    
      });
    }
    if(requestHeaders.length >0){
        requestHeaders.forEach(element => {
        let param  =  {"parameterType": "HEADER_PARAM", "key":element.key,"value":element.value}
        params.push(param);    
      });
    }

    if(cookieVariables.length >0){
        cookieVariables.forEach(element => {
        let param  =  {"parameterType": "COOKIE_PARAM", "key":element.key,"value":element.value}
        params.push(param);    
      });
    }

    if(addifyVariables.length >0){
      addifyVariables.forEach(element => {
        let param  =  {"parameterType": "ADDIFY_PARAM", "key":element.key,"value":element.value}
        params.push(param);    
      });
    }
    if(evaluateFunctionVariables.length >0){
      evaluateFunctionVariables.forEach(element => {
        let param  =  {"parameterType": "EVAL_PARAM", "key":element.key,"value":element.value}
        params.push(param);    
      });
    }
    if(formFunctionVariables.length >0){
      formFunctionVariables.forEach(element => {
        let param  =  {"parameterType": "FORM_PARAM", "key":element.key,"value":element.value}
        params.push(param);    
      });
    }
  

    let availableParams = params

    let outputFields = {}
    if(outputField.length ){
      outputField.forEach(element => {
        outputFields[element.key] = element.value
      });
    }        

    onAdd({ scenarioId, type,resource,scenario, url, method, contentType, httpStatusCode, input, output, outputFields, availableParams, csvson, stepInfo })
    reset();
   
  }


  const reset = () => {
    setScenarioId('')
    setType('')
    setResource('')
    setScenario('')
    setUrl('')
    setMethod('')
    setContentType('')
    setHttpStatusCode('')
    setInput('')
    setOutput('')
    setCsvson('')
    setStepInfo('')
    setRequestHeaders([])
    setEvaluateFunctionVariables([])
    setAddifyVariables([])
    setFormFunctionVariables([])
    setOutputField([])
    setStoreResponseVariables([])
    setCookieVariables([]);
    setRefresh(!refresh)
  }

  const onStatusCodeSelect = (code) =>{
    setHttpStatusCode(code)
  }

  useEffect(() => {
    setReload(!reload)
  }, [request])

 useEffect(() => {
   doEditRefresh(prev => prev + 1); 
 }, [setHttpStatusCode]);

 

  return (
    <form className='add-form' onSubmit={onSubmit}>
    <div className="flexbox-container">
    <div className="row"> 
        
      <div className='form-control'>
        <label>Type</label>
        <select
          className="drop-down form-control-grid"
          placeholder=' '
          value = {type}
          onChange={(e) => setType(e.target.value)}
        >
          <option>Select</option>
          <option value="REST">REST</option>
          <option value="DB">DB</option>
          <option value="KAFKA">KAFKA</option>
          <option value="AMQ">AMQ</option>

        </select>
      </div>
      <div className='form-control'>
        <label>Resource</label>
        <input
          type='text'
          placeholder=' '
          value={resource}
          onChange={(e) => setResource(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label>Action</label>
        <select
          className="drop-down form-control-grid"
          placeholder=' '
          value ={method}
          onChange={(e) => setMethod(e.target.value)}
        >
          <option>Select</option>
          <option value="POST">POST</option>
          <option value="GET">GET</option>
          <option value="PUT">PUT</option>
          <option value="PATCH">PATCH</option>
          <option value="DELETE">DELETE</option>

        </select>
      </div>
      </div>
      <div className="row"> 
      <div className='form-control'>
        <label>Scenario/TestCase ID</label>
        <input
          type='text'
          placeholder=''
          value={scenarioId}
          onChange={(e) => setScenarioId(e.target.value)}
        />
      </div>
     <div className='form-control'>
      <label>Scenario/TestCase Desc</label>
      <input
        type='text'
        placeholder=' '
        value={scenario}
        onChange={(e) => setScenario(e.target.value)}
      />
      </div>
      <div className='form-control'>
        <label>API Endpoint URL</label>
        <input
          type='text'
          placeholder=' '
          value={url}
          onChange={(e) => setUrl(e.target.value)}
        />
      </div>
     
      </div>
      <div className="row"> 
        <div className='form-control'>
        <label>ContentType</label>
        <input
          type='text'
          placeholder=' '
          value={contentType}
          onChange={(e) => setContentType(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label>StatusCode</label>
        <StatusCodes 
          refresh = {refresh}
          editRefresh = {editRefresh}
          statusCodeValue = {httpStatusCode}
          onStatusCodeSelect = {onStatusCodeSelect}
          >
          </StatusCodes>
      </div>
      <div className='form-control'>
        <label>RequestContent</label>
        <textarea
          className="form-control-area"
          placeholder=' '
          value={input}
          onChange={(e) => setInput(e.target.value)}
        ></textarea>
      </div>
      </div>
      <div className="row"> 
      <div className='form-control'>
      <label>Form Params</label> 
        <Grid 
           refresh = {refresh}
           headerType= {formvariables}
          data={formFunctionVariables} 
          onAdd = { e => setFormFunctionVariables(e.target.value)}
          />
        {/* <label>StepInfo</label>
        <input
          type='text'
          placeholder=' '
          value={stepInfo}
          onChange={(e) => setStepInfo(e.target.value)}
        /> */}
      </div>

      <div className='form-control'>
        <label>RequestHeaders</label> 
        <Grid 
          refresh = {refresh}
          headerType= {headers}
          data={requestHeaders} 
          onAdd = { (rows) => setRequestHeaders(rows)}
          />
      </div>
      
      <div className='form-control'>
        <label>CookieVariables </label> 
        <Grid 
          refresh = {refresh}
          headerType= {cookies}
          data={cookieVariables} 
          onAdd = { (rows) => setCookieVariables(rows)}
          />
      </div>
      
      </div>
      <div className="row"> 
        <div className='form-control'>
          <label>ResponseContent</label>
          <textarea
          className="form-control-area"
          placeholder=' '
            value={output}
            onChange={(e) => setOutput(e.target.value)}
          />
      </div>
      <div className='form-control'>
        <label>AddifyVariables</label> 
        <Grid 
          refresh = {refresh}
          headerType= {addify}
          data={addifyVariables}
          onAdd = { (rows) => setAddifyVariables(rows)}
        />
      </div>
         <div className='form-control'>
        <label>EvaluateFunctionVariables </label> 
        <Grid 
          refresh = {refresh}
          headerType= {evaluateFunctions}
          data={evaluateFunctionVariables}
          onAdd = { (rows) => setEvaluateFunctionVariables(rows)}
        />
        </div>
        </div>
      <div className="row"> 
      <div className='form-control'>
        <label>Csvson</label>
        <textarea
          className="form-control-area"
          type='text'
          placeholder=' '
          value={csvson}
          onChange={(e) => setCsvson(e.target.value)}
        />
      </div>
      <div className='form-control'>
        <label>ResponseByFields</label> 
        <Grid 
          refresh = {refresh}
          headerType= {responseVariables}
          data={outputField}
          onAdd = { rows => setOutputField(rows)}
        />
      </div>
         <div className='form-control'>
        <label>StoreResponseVariables </label> 
        <Grid 
          refresh = {refresh}          
          headerType= {variables}
          data={storeResponseVariables}
          onAdd = { (rows) => setStoreResponseVariables(rows)}
        />
       
      </div>
      </div>
      </div>
      <input type='submit' value='Refresh Request' className='btn btn-block' onClick = { e => {e.preventDefault(); reset();}}/>
      <input type='submit' value='Save Request' className='btn btn-block' />
    </form>
  )
}

export default AddRequest
