//document.addEventListener('DOMContentLoaded', function() {
//window.onload = function() {
//    //タブに対してクリックイベントを適用
//    const tabs = document.getElementById('tab');
//    console.log(tabs)
//    for(let i=0; i<tabs.length; i++) {
//        tabs[i].addEventListener('click', function() {
//            document.form.submit();
//        });
//    }
//};
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
