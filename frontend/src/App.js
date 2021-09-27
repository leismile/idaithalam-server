import React, { useState } from 'react'
import { BrowserRouter as Router, Route } from 'react-router-dom'
import Header from './components/Header'
import Footer from './components/Footer'
import AddRequest from './components/AddRequest'
import About from './components/About'
import DownloadLink from "react-download-link";
import Uploader from "./components/Upload";
import OverviewFlow from './components/OverviewFlow';
import Button from './components/Button'
import Popup from './components/Popup' 

const App = () => {
  const [showAddRequest, setShowAddRequest] = useState()
  const [showAddFlow, setShowAddFlow] = useState()
  const [requests, setRequests] = useState([])
  const [request, setRequest] = useState()
  const [elements, setElements] = useState([])
  const [modal, setModal] = useState(false);
  const [children, setChilderen] = useState();
  const [scenarioId, setScenarioId] = useState();
  const [reload, setReload] = useState(false)
  
  
  
  // Fetch Request
  const elementbuilder = (id, title) => {
    return ({
       id: id ,
        data: {
        label: title,
       
      }
      ,
      position: { x: 100, y: id*100 },  
      style: {
        border: '1px solid #004080',
        width: 300,
      },
    })
  }


  // Fetch 
  const elementbuilderFlow = (startId, endId) => {

    return (
      { id: 'e'+startId +"-"+endId, 
      source: startId, 
      target: endId, 
      animated: true,
      style: { stroke: '#89bf04' },
      arrowHeadType: 'arrowclosed'

    })
  }

  

  
  // Fetch 
  const flowChartbuilder = (tmpList) => {
    var processingList = tmpList
    if(processingList == null){
      processingList = requests; 
    }
    let addEements = [];
    let count = 1;
    addEements.push(elementbuilder(count, "Start"))
    processingList.forEach(request => {
      count = count+1
      addEements.push(elementbuilder(count, request.scenarioId))
    });
    count = count+1
    if(processingList.length >0){
      addEements.push(elementbuilder(count, "End"))
    } else {
      addEements.push(elementbuilder(2, "End"))
    }
     let startCount = 1;
     let endCount = 2;
     addEements.push(elementbuilderFlow(startCount, endCount))
     processingList.forEach(request => {
      startCount = startCount + 1;
      endCount = endCount+1;
      addEements.push(elementbuilderFlow(startCount, endCount))
    });
    if(processingList.length){
        startCount = startCount + 1;
        endCount = endCount+1;
        addEements.push(elementbuilderFlow(startCount, endCount))
    }
    return addEements;
  }


  // Add request
  const addRequest = async (addRequestMsg) => {
    let newRequests = []
    let isNotUpdate = true;
    requests.forEach(request =>{
      if(request.scenarioId === addRequestMsg.scenarioId){
        newRequests.push(addRequestMsg);
        isNotUpdate = false;
      } else {
        newRequests.push(request);
      }
    });
    if(isNotUpdate){  
      newRequests.push(addRequestMsg)  
    }
    setRequests(newRequests)
    setElements(flowChartbuilder())
    setReload(!reload)
  }

 // Upload request
  const uploadRequests = async (requestArray) => {
    const objArry = JSON.parse(requestArray.text);
    for(var i=0; i < objArry.length; i++){
      requests.push(objArry[i])
      }
      setElements( flowChartbuilder())
      setReload(!reload)
    }

  // Delete request
  const deleteRequest = async (id) => {
    let tmpList = [];
    requests.forEach(req =>{
      if(req.scenarioId !== id){
        tmpList.push(req)
      }
    });
    setRequests(tmpList)
  }   


  const modalClose = () => {
    setModal(false);
  }

  const onChildrenSelect = (e) =>{
    e.preventDefault();
    requests.forEach(request =>{
      if(request.scenarioId===e.target.textContent){
        setChilderen(JSON.stringify(request));
        setModal(true)
        setScenarioId(request.scenarioId)
      }
      });
  }
   const onChildrenDelete = (e) =>{
      e.preventDefault();
      deleteRequest(scenarioId)
      let tmpList = [];
      requests.forEach(req =>{
        if(req.scenarioId !== scenarioId){
          tmpList.push(req)
        }
      });
      setModal(false)
      //setShowAddFlow(false)  
      setElements( flowChartbuilder(tmpList))
      setReload(!reload)   
  }
  

  const onLoad = (e) =>{ 
    setShowAddFlow(false)
    setElements( flowChartbuilder())
    setShowAddFlow(!showAddFlow)
 }


const onChildrenEdit = (e) =>{
  e.preventDefault();
  let editReq = null;
  requests.forEach(req =>{
    if(req.scenarioId === scenarioId){
      editReq = req
    }
    });
    setModal(false)
    setShowAddFlow(false)   
  if( editReq.outputFields ){
    editReq.outputField = []
    var count = 0; 
    for (var key in editReq.outputFields) {
      editReq.outputField.push({key: key, value: editReq.outputFields[key], index : count++})
    }
  }
  if( editReq.availableParams ){
    editReq.requestHeaders = []
    editReq.cookieVariables = []
    editReq.storeResponseVariables = []
    editReq.formFunctionVariables = []
    editReq.addifyVariables = []
    editReq.evaluateFunctionVariables = []
    var hcount = 0;
    var scount = 0;
    var ccount = 0;
    var fcount = 0;
    var acount = 0;
    var ecount = 0;
    editReq.availableParams.forEach( param =>
      {
        if("HEADER_PARAM" === param.parameterType) {
          if(param.key !== 'contentType') {
            editReq.requestHeaders.push({key: param.key, value: param.value, index : hcount++ });
          }
        } 
        else if("COOKIE_PARAM" === param.parameterType) {
          editReq.cookieVariables.push({key: param.key, value: param.value, index : ccount++ });
        } 
        else if("STORAGE_PARAM" === param.parameterType) {
          editReq.storeResponseVariables.push({key: param.key, value: param.value, index : scount++ });
        } 
        if("FORM_PARAM" === param.parameterType) {
            editReq.formFunctionVariables.push({key: param.key, value: param.value, index : fcount++ });
        } 
        else if("ADDIFY_PARAM" === param.parameterType) {
          editReq.addifyVariables.push({key: param.key, value: param.value, index : acount++ });
        } 
        else if("EVAL_PARAM" === param.parameterType) {
          editReq.evaluateFunctionVariables.push({key: param.key, value: param.value, index : ecount++ });
        } 
      }
    );
  }
  setRequest(editReq)
}

 const removeEmptyOrNull = (obj) => {
 
  Object.keys(obj).forEach(k =>
    (obj[k] && typeof obj[k] === 'object')  
    &&(removeEmptyOrNull(obj[k]))||
    (!obj[k] && (obj[k] !== undefined || 
      k === 'requestHeaders' || k ==='addifyVariables' || k === 'cookieVariables' || 
      k === 'evaluateFunctionVariables' || k === 'formFunctionVariables' || k === 'outputFields')) 
      && delete obj[k]);
  return obj;

};


  return (
    <Router>
      <div className='container'>
        <Header
          onAdd={() => setShowAddRequest(!showAddRequest)}
          showAdd={showAddRequest}
        />
        <Route
          path='/'
          exact
          render={(props) => (
            <>
                <div className='subheader'>  <h3 className="blue-class">Add Request</h3>
                </div>
              <div>
              <hr/>

              {showAddRequest && <AddRequest onAdd={addRequest}  request={request} />}
              
              <br/>
                <div className='subheader'>  <h3 className="blue-class">Upload Virtualan Collection</h3>
                </div>
                <hr/>
                <br/>
                <div className=" mt-5">
                  <Uploader className="form-control" onUpload={uploadRequests}/>
                </div>
              <br/>
              <div className='subheader'>  <h3 className="blue-class">Download Requests</h3>
                <div>
                  {/* <Button 
                  color='#89bf04'
                  text= 'Download'
                  onClick={onAdd}
                />  */}
                </div>
                </div>
              <hr/>
              <br/>
              {requests.length > 0 ? (
                <div>              
                <DownloadLink  className="blue-class"
                    label="Save"
                    filename="virtualan-collection-workflow.json"
                    exportFile={() => JSON.stringify(removeEmptyOrNull(requests), null, '\t')}
                />
                
                </div>
              ) : (
                'No Requests To Show'
              )}
                <br/>                
                <div className='subheader'>  <h3 className="blue-class">Work Flow  </h3>
                  <Button 
                  color={showAddFlow ? '#800040' : '#89bf04'}
                  text={showAddFlow ? 'Close' : 'Open'}
                  onClick={onLoad}
                /> 
                </div>  
                <hr/>
                <br/>
                {requests.length > 0 ? ( 
                  showAddFlow && <OverviewFlow reload={reload} modal={modal}  onClickElementDelete={e => onChildrenDelete(e)} scenarioId={ scenarioId } initialElements ={elements}  onElementClick={e => onChildrenSelect(e)} />
                ) : (
                  'No workflow To Show'
                )}
                {/* <Button 
                    color={modal ? '#800040' : '#89bf04'}
                    text={modal ? 'Close' : 'Open Request'}
                    onClick={e => modalOpen(e)}
                  />  */}
                   { modal && <Popup show={modal} scenarioId={ scenarioId } removeRequest = {e => onChildrenDelete} onEdit = {e => onChildrenEdit}  children={children} handleClose={e => modalClose(e)}>
                  </Popup>
                  }
                <br/>  
                <br/>
                </div>
            </>
            
          )}
          
        />

        <Route path='/about' component={About} />
      
        <Footer />
      
      </div>
    </Router>
  )
}

export default App
