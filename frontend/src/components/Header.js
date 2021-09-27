import PropTypes from 'prop-types'
import { useLocation } from 'react-router-dom'
import Button from './Button'
import React from 'react'

const Header = ({ title, onAdd, showAdd }) => {
  const location = useLocation()

  return (
    <header className='header'>
      <img width="110px" height="80px" src="logo192.png" alt="Virtualan"/><h1 className="brown-class">{title}</h1>
      {location.pathname === '/' && (
        <Button 
          color={showAdd ? '#800040' : '#89bf04'}
          text={showAdd ? 'Close' : 'Add'}
          onClick={onAdd}
        />
      )}
    </header>
  )
}

Header.defaultProps = {
  title: 'Idaithalam ',
}

Header.propTypes = {
  title: PropTypes.string.isRequired,
}

// CSS in JS
// const headingStyle = {
//   color: 'red',
//   backgroundColor: 'black',
// }

export default Header
