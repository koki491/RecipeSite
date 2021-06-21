window.onload = function() {
    const tabs = document.querySelectorAll("[id = 'tab']");
    let element = document.querySelectorAll('form');
    for(let i=0; i<tabs.length; i++) {
        element[i].id = 'form' + (i+1);
    }
}

function myFunction(event) {
    var value = event.value;
    var formName = 'form' + value;
    document.getElementById(formName).submit();
}
