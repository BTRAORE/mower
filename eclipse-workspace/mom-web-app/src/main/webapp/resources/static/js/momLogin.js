$(window).on('load',function(){
        $('#loginModal').modal('show');
});
$(document).ready(function () {
	$('.close').on('click', function(){
		parent.window.location="/welcome";
});});