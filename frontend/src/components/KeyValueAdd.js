import React, { useState } from "react";
import { Link } from 'react-router-dom';
import { FaPlus } from 'react-icons/fa';


 const  KeyValueAdd = ({onParamAdd}) => {
  const [key, setKey] = useState('')
  const [value, setValue] = useState('')


    return (
      <div className="row">

        <div className='form-control-45'>
        <input
          type='text'
          placeholder=' '
          value = {key}
          onChange={(e) => setKey(e.target.value)}

        />
      </div>
      <div className='form-control-45'>
        <input
          type='text'
          placeholder=' '
          value = {value}
          onChange={(e) => setValue(e.target.value)}
        />

      </div>
      <div className='form-control-10'>
      <i className="fas fa-lg fa-trash-alt"></i>
      <Link  to="" onClick={(e) =>{onParamAdd(key, value);setKey(''); setValue('') }}>  <FaPlus /></Link>
      </div>
    </div>);
  }

export default KeyValueAdd