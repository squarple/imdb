debugger
    var storedHash = window.location.hash;
    function changeHashOnLoad() {
    window.location.hash = "1";
}
    window.onhashchange = function () {
    window.location.hash = storedHash;
}