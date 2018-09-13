$(document).ready(function () {

    $(document).on('click', '#mainNavbar li', function() {
        $("#mainNavbar li").removeClass("active");
        $(this).addClass("active");
    });
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
});