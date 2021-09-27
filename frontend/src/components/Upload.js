
import React from 'react'
import { useState } from 'react'

const Uploader = ({ onUpload }) => {
	const [fileInput,] = useState('')
	
	const handleSubmit = (e)  => {
		e.preventDefault();
		if(e & e.target && e.target.files[0] != null){	
			const read = new FileReader();
			read.onload = async (e) => {
				const text = (e.target.result)
				onUpload({text})	
			}
			read.readAsText(e.target.files[0])
		}
	}
	
	
	const handleInputChange = (e)  => {
		e.preventDefault();
		if(e && e.target && e.target.files[0] ){	
			const read = new FileReader();
			read.onload = async (e) => {
				const text = (e.target.result)
				onUpload({text})	
			}
			read.readAsText(e.target.files[0])
		}
	}

	
	return (
		<form onSubmit={handleSubmit}>
			<label>
				<input type="file"
					value={fileInput}
					onChange={handleInputChange}					
					className="form-control" />     
				{/* <input type='submit' value='Load Request' className='btn btn-block' /> */}

			</label>
			{/* <br />
			{
				this.state.fileName
					&& <h4 className="mt-3">
							File: <span className="text-danger">{this.state.fileName}</span>
						</h4>
			} */}
		</form>
	);

}

export default Uploader