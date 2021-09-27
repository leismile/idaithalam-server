import React, { useState, useEffect } from "react";
import ReactDataGrid from "react-data-grid";
import KeyValueAdd  from './KeyValueAdd'
import { FaTrash } from 'react-icons/fa';


const Grid = ({data, headerType, onAdd, refresh, reload}) => {

  const [rows, setRows] = useState(data && data.length > 0 ? data : []);

  const onParamAdd = (key, value) => {
    if(key && key.length >0 && value && value.length >0){
      rows.push({key: key, value: value, index : rows.length})
      onAdd(rows)
    }
  }

  useEffect(() => {
    setRows([])
  },[refresh]);


  useEffect(() => {
    setRows(data)
  },[reload]);


  const getCellActions = (column, row) => {
    const cellActions = [
      {
        icon: <FaTrash  />,
        callback: () => {
          const rowss = [...rows];
          if(rowss.length ===1 ){
            setRows ([] );
          } else {
            rowss.splice(row.index, 1); //
            setRows (rowss );
          }
          onAdd(rows)
        }
      }
    ];
    return column.key === "action" ? cellActions : null;
  };

  const onGridRowsUpdated = ({ fromRow, toRow, updated }) => {
    setRows(state => {
      const rows = state.rows.slice();
      for (let i = fromRow; i <= toRow; i++) {
        rows[i] = { ...rows[i], ...updated };
      }
      return { rows };
    });
    onAdd(rows)
  };
  return (
    <div>
      <KeyValueAdd onParamAdd={onParamAdd}/> 
      <ReactDataGrid minHeight='140px'
        columns={headerType}
        rowGetter={i => rows[i]}
        rowsCount={rows.length}
        onGridRowsUpdated={onGridRowsUpdated}
        enableCellSelect={true}
        getCellActions={getCellActions}
      />
    </div>
  );
}

export default Grid;