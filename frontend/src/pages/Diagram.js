import React, { useState, useEffect } from 'react'
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from 'chart.js';

import { Line } from 'react-chartjs-2';

ChartJS.register(
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend
);


const Diagram = () => {
  const [chart, setChart] = useState({})
  const [matchlabels, setMatchLabels] = useState({})

  useEffect(()=>{
    fetch("http://localhost:8080/match/getMatchLabels")
    .then(res=>res.json())
    .then((result)=>{
      setMatchLabels(result);
    }
  )
  },[])

  useEffect(()=>{
    fetch("http://localhost:8080/match/getResults")
    .then(res=>res.json())
    .then((result)=>{
      setChart(result);
    }
  )
  },[])

  var data = {
    labels: [1, 2, 3, 4, 5],
    datasets: [{
      label: ["Ajax Season Overview"],
      data: chart,
      backgroundColor: ['rgb(255, 0, 0)'],
      borderColor: ['rgb(0, 0, 0)'],
      borderWidth: 2
    }],
    innerWidth: 20
  };

  var options = {
    maintainAspectRatio: false,
    scales: {
        color: '#ffffff',
        
    },
    legend: {
      labels: {
        fontSize: 25,
      },
    },
  }

  return (
    <div style={{ width: '50%', margin: 'auto' }}>
      <Line
        data={data}
      />
    </div>
  );
}

export default Diagram