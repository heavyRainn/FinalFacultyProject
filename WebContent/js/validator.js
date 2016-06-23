/**
 * 
 */
(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#faculty-form").validate({
				rules : {
					idFaculty : {
						required : true
					},
					name : "required",
					startDate : {
						required : true,
						date : true
					},
					endDate : {
						required : true,
						date : true
					}
				},
				messages : {
					idFaculty : "Please select a faculty<br/>",
					name : " Please enter name of the faculty",
					startDate : {
						required : "Please enter the start date",
						date : "Please enter the date"
					},
					endDate : {
						required : "Please enter the end date",
						date : "Please enter the date"
					},
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);

(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#faculty-form").validate({
				rules : {
					idFaculty : {
						required : true
					},
				},
				messages : {
					idFaculty : "Please select a faculty<br/>",
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);

(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#create-form").validate({
				rules : {
					idStudent : {
						required : true
					},
					mark : "required",
					description : "required",
				},
				messages : {
					idStudent : "Please select a student<br/>",
					mark : "Please enter the mark",
					description : "Please enter the description",
				},

				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);

(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#feedback-form").validate({
				rules : {
					idFeedback : {
						required : true
					},
				},
				messages : {
					idFeedback : "Please select a feedback<br/>",
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);

(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#login-form")
					.validate(
							{
								rules : {
									login : "required",
									password : {
										required : true,
										minlength : 3
									},
								},
								messages : {
									login : " Please enter your login",
									password : {
										required : " Please provide a password",
										minlength : " Your password must be at least 3 characters long"
									},
								},
								submitHandler : function(form) {
									form.submit();
								}
							});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);

(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#register-form").validate({
				rules : {
					name : "required",
					startDate : {
						required : true,
						date : true
					},
					endDate : {
						required : true,
						date : true
					}
				},
				messages : {
					name : " Please enter name of the faculty",
					startDate : {
						required : "Please enter the start date",
						date : "Please enter the date"
					},
					endDate : {
						required : "Please enter the end date",
						date : "Please enter the date"
					},
				},
				submitHandler : function(form) {
					form.submit();
				}
			});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);

(function($, W, D) {
	var JQUERY4U = {};

	JQUERY4U.UTIL = {
		setupFormValidation : function() {
			// form validation rules
			$("#register-form-user")
					.validate(
							{
								rules : {
									login : "required",
									password : {
										required : true,
										minlength : 3
									},
									mail : {
										required : true,
										email : true
									},
									name : "required",
									surname : "required",
								},
								messages : {
									login : " Please enter your login",
									password : {
										required : " Please provide a password",
										minlength : " Your password must be at least 3 characters long"
									},
									mail : " Please enter a valid email address",
									name : " Please enter your name",
									surname : " Please enter your surname",
								},
								submitHandler : function(form) {
									form.submit();
								}
							});
		}
	}

	// when the dom has loaded setup form validation rules
	$(D).ready(function($) {
		JQUERY4U.UTIL.setupFormValidation();
	});

})(jQuery, window, document);