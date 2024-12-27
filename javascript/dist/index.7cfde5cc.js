document.getElementById("add").addEventListener("click", ()=>calculate("add"));
document.getElementById("subtract").addEventListener("click", ()=>calculate("subtract"));
document.getElementById("multiply").addEventListener("click", ()=>calculate("multiply"));
document.getElementById("divide").addEventListener("click", ()=>calculate("divide"));
function calculate(operation) {
    const num1 = parseFloat(document.getElementById("num1").value);
    const num2 = parseFloat(document.getElementById("num2").value);
    let result;
    if (isNaN(num1) || isNaN(num2)) result = "Please enter valid numbers!";
    else switch(operation){
        case "add":
            result = num1 + num2;
            break;
        case "subtract":
            result = num1 - num2;
            break;
        case "multiply":
            result = num1 * num2;
            break;
        case "divide":
            result = num2 !== 0 ? num1 / num2 : "Cannot divide by zero";
            break;
        default:
            result = "Invalid operation!";
    }
    document.getElementById("result").textContent = result;
}

//# sourceMappingURL=index.7cfde5cc.js.map
