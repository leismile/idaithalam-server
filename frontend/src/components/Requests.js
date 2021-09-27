import Request from './Request'

const Requests = ({ requests, onDelete, onToggle }) => {
  return (
    <>
      {requests.map((request, index) => (
        <Request key={index} request={request} onDelete={onDelete} onToggle={onToggle} />
      ))}
    </>
  )
}

export default Requests
