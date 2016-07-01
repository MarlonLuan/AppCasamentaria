$(document).ready(function() {

	$("#ajaxButton").click(function(evt) {
		evt.preventDefault();

		var href = $(this).attr('href');
		$.ajax({
			type : "GET",
			url : href,
			beforeSend : function() {
				$('.loader').css({
					display : "block"
				});
			},
			success : function(data) {
				$('#ajaxCadastro').html(data);
			},
			complete : function() {
				$('.loader').css({
					display : "none"
				});
			}
		});
	});
});