import React from "react";
import { useState, useEffect } from 'react'

const StatusCodes = ({statusCodeValue, editRefresh, onStatusCodeSelect,refresh}) => {
    
    const [statusCode, setStatusCode] = useState('')

    
        useEffect(() => {
            setStatusCode(statusCodeValue)
          }, [editRefresh, refresh]);
    
          
    return <div className="form-control-area">

        <select
          className="drop-down  form-control-area"
          placeholder=' '
          value = {statusCode}
          onChange={(e) => { setStatusCode(e.target.value); onStatusCodeSelect(e.target.value) } }
        >
            <option >Select</option>
            <option value='200' >200 - OK</option>
            <option value='201' >201 - Created</option>
            <option value='200' >200 - OK</option>
            <option value='201' >201 - Created</option>
            <option value='202' >202 - Accepted</option>
            <option value='203' >203 - Non-Authoritative Information</option>
            <option value='204' >204 - No Content</option>
            <option value='205' >205 - Reset Content</option>
            <option value='206' >206 - Partial Content</option>
            <option value='207' >207 - Multi-Status (WebDAV)</option>
            <option value='208' >208 - Already Reported (WebDAV)</option>
            <option value='226' >226 - IM Used</option>
            <option value='300' >300 - Multiple Choices</option>
            <option value='301' >301 - Moved Permanently</option>
            <option value='302' >302 - Found</option>
            <option value='303' >303 - See Other</option>
            <option value='304' >304 - Not Modified</option>
            <option value='305' >305 - Use Proxy</option>
            <option value='306' >306 - (Unused)</option>
            <option value='307' >307 - Temporary Redirect</option>
            <option value='308' >308 - Permanent Redirect (experimental)</option>
            <option value='400' >400 - Bad Request</option>
            <option value='401' >401 - Unauthorized</option>
            <option value='402' >402 - Payment Required</option>
            <option value='403' >403 - Forbidden</option>
            <option value='404' >404 - Not Found</option>
            <option value='405' >405 - Method Not Allowed</option>
            <option value='406' >406 - Not Acceptable</option>
            <option value='407' >407 - Proxy Authentication Required</option>
            <option value='408' >408 - Request Timeout</option>
            <option value='409' >409 - Conflict</option>
            <option value='410' >410 - Gone</option>
            <option value='411' >411 - Length Required</option>
            <option value='412' >412 - Precondition Failed</option>
            <option value='413' >413 - Request Entity Too Large</option>
            <option value='414' >414 - Request-URI Too Long</option>
            <option value='415' >415 - Unsupported Media Type</option>
            <option value='416' >416 - Requested Range Not Satisfiable</option>
            <option value='417' >417 - Expectation Failed</option>
            <option value='418' >418 - I'm a teapot (RFC 2324)</option>
            <option value='420' >420 - Enhance Your Calm (Twitter)</option>
            <option value='422' >422 - Unprocessable Entity (WebDAV)</option>
            <option value='423' >423 - Locked (WebDAV)</option>
            <option value='424' >424 - Failed Dependency (WebDAV)</option>
            <option value='425' >425 - Reserved for WebDAV</option>
            <option value='426' >426 - Upgrade Required</option>
            <option value='428' >428 - Precondition Required</option>
            <option value='429' >429 - Too Many Requests</option>
            <option value='431' >431 - Request Header Fields Too Large</option>
            <option value='444' >444 - No Response (Nginx)</option>
            <option value='449' >449 - Retry With (Microsoft)</option>
            <option value='450' >450 - Blocked by Windows Parental Controls (Microsoft)</option>
            <option value='451' >451 - Unavailable For Legal Reasons</option>
            <option value='499' >499 - Client Closed Request (Nginx)</option>
            <option value='500' >500 - Internal Server Error</option>
            <option value='501' >501 - Not Implemented</option>
            <option value='502' >502 - Bad Gateway</option>
            <option value='503' >503 - Service Unavailable</option>
            <option value='504' >504 - Gateway Timeout</option>
            <option value='505' >505 - HTTP Version Not Supported</option>
            <option value='506' >506 - Variant Also Negotiates (Experimental)</option>
            <option value='507' >507 - Insufficient Storage (WebDAV)</option>
            <option value='508' >508 - Loop Detected (WebDAV)</option>
            <option value='509' >509 - Bandwidth Limit Exceeded (Apache)</option>
            <option value='510' >510 - Not Extended</option>
            <option value='511' >511 - Network Authentication Required</option>
            <option value='598' >598 - Network read timeout error</option>
            <option value='599' >599 - Network connect timeout error</option>
        </select>
    </div>
}
export default StatusCodes
