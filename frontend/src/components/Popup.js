import React from "react";
import Button from './Button'

const Popup = ({  handleClose,  children, removeRequest, onEdit }) => {
  
  
  
  return (
    <div className='blur-box'>
      <div className="modal-container">
         <header className='header'><h2>View Request</h2>  <div><Button 
                        color='#800040'
                        text='Edit' 
                        onClick={onEdit()}                  
                        /> <Button 
                        color='#800040'
                        text='Delete' 
                        onClick={removeRequest()}                  
                        /> <Button 
                        color='#89bf04'
                        text='Close' 
                        onClick={handleClose}                 
                        /></div> </header>
            <div>
                <hr/>
                <br/>
                
            { JSON.stringify(JSON.parse(children), null, 10)}   
            </div>
      </div>
    </div>
  );
};

export default Popup;
