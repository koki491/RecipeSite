window.onload = function() {
    const tabs = document.querySelectorAll("[id = 'form']");
    let element = document.querySelectorAll('form');
    for(let i=0; i<tabs.length; i++) {
        element[i+1].id = 'form' + (i+1);
    }
}

function myFunction(event) {
    var value = event.value;
    console.log(value);
    var formName = 'form' + value;
    document.getElementById(formName).submit();
}
