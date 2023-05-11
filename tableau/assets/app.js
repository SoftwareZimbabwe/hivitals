// Blood Pressure Chart
var bloodPressureChart = new Chart(document.getElementById("blood-pressure-chart"), {
    type: 'line',
    data: {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      datasets: [{ 
          data: [120, 122, 118, 125, 130, 128, 130, 132, 135, 137, 135, 138],
          label: "Blood Pressure",
          borderColor: "#3e95cd",
          fill: false
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Blood Pressure Over Time'
      }
    }
  });
  
  // Blood Glucose Chart
  var bloodGlucoseChart = new Chart(document.getElementById("blood-glucose-chart"), {
    type: 'bar',
    data: {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      datasets: [
        {
          label: "Blood Glucose",
          backgroundColor: "#3e95cd",
          data: [96, 105, 98, 100, 102, 110, 112, 107, 100, 97, 95, 99]
        }
      ]
    },
    options: {
      legend: { display: false },
      title: {
        display: true,
        text: 'Blood Glucose Over Time'
      }
    }
  });
  
  // Weight Chart
  var weightChart = new Chart(document.getElementById("weight-chart"), {
    type: 'line',
    data: {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      datasets: [{ 
          data: [75, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87],
          label: "Weight",
          borderColor: "#3e95cd",
          fill: false
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Weight Over Time'
      }
    }
  });
  
  // Height Chart
  var heightChart = new Chart(document.getElementById("height-chart"), {
    type: 'line',
    data: {
      labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
      datasets: [{ 
          data: [170, 170, 171, 172, 172, 173, 174, 174, 175, 175, 176, 176],
          label: "Height",
          borderColor: "#3e95cd",
          fill: false
        }
      ]
    },
    options: {
      title: {
        display: true,
        text: 'Height Over Time'
      }
    }
  });
  
  // Pie Chart
  var pieData = {
    datasets: [{
      data: [20, 30, 50],
      backgroundColor: [
        '#3e95cd',
        '#8e5ea2',
        '#3cba9f'
      ]
    }],
    labels: [
      'Underweight',
      'Normal',
      'Overweight'
    ]
  };
  
  var pieOptions = {
    title: {
      display: true,
      text: 'BMI Classification'
    }
  };
  
  var pieChart = new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: pieData,
    options: pieOptions
  });
  
  // Bar Chart
  var barData = {
    labels: ["Normal", "Elevated", "Hypertension Stage 1", "Hypertension Stage 2"],
    datasets: [
      {
        label: "Systolic",
        backgroundColor: "#3e95cd",
        data: [30, 40, 20, 10]
      },
      {
        label: "Diastolic",
        backgroundColor: "#8e5ea2",
        data: [20, 30, 40, 10]
      }
    ]
  };
  
  var barOptions = {
    title: {
      display: true,
      text: 'Blood Pressure Classification'
    },
    scales: {
      xAxes: [{
        stacked: true
      }],
      yAxes: [{
        stacked: true
      }]
    }
  };
  
  var barChart = new Chart(document.getElementById("bar-chart"), {
    type: 'bar',
    data: barData,
    options: barOptions
  });