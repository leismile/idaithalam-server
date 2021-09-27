import { FaTimes } from 'react-icons/fa'

const Request = ({ request, onDelete, onToggle }) => {
  return (
    <div>
      <h3>        
        {request.scenarioId}{' '}
        <FaTimes
          style={{ color: 'red', cursor: 'pointer' }}
          onClick={() => onDelete(request.scenarioId)}
        />
      </h3>
      { <p>{JSON.stringify(request, null, '\t')}</p> }
    </div>
  )
}

export default Request
