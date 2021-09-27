import React, { useState, useEffect } from 'react';
import styled from "styled-components";
import ReactFlow, {
  ReactFlowProvider,
  Controls,
  addEdge,
} from 'react-flow-renderer';


const WrapperStyled = styled.div`
  width: 100vw;
  height: 80vh;
  overflow: hidden;
`;
 
  const OverviewFlow = ({initialElements,  onElementClick, onClickElementDelete, reload}) => {

  const [elements, setElements] = useState(initialElements);
  const [rfInstance, setRfInstance] = useState(null);
  

  const onLoad = (reactFlowInstance) => {
    setRfInstance(reactFlowInstance)
    reactFlowInstance.fitView();
  };

  useEffect(()  => {
     setElements(initialElements);
  },[reload]);


  useEffect(() => {
    if (rfInstance) {
      rfInstance.fitView();
    }
  }, [rfInstance, elements]);      


  const onConnect = (params) => setElements((els) => addEdge(params, els));

  return (
    <WrapperStyled>
      <ReactFlowProvider>
        <ReactFlow style={{ height: 725 }} 
          elements={elements}
          onElementsRemove={onClickElementDelete}
          onConnect={onConnect}
          onLoad={onLoad}
          snapToGrid={true}
          snapGrid={[15, 15]}
          onElementClick={onElementClick}
          >
            <Controls/>
        </ReactFlow>
    </ReactFlowProvider>
    </WrapperStyled>
  );
};


export default OverviewFlow;