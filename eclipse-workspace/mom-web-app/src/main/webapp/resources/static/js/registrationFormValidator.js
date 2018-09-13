/**
 * Pre-validate the registration form
 *@author Sam <sam@palo-verde.us>
 */
function RegistrationForm() {
	this.form = $('registrationForm');
	this.form.onsubmit = function () {return false;};
	this.errorContainer = $('registrationErrors');
	this.errors = Array();
	this.usernameChecked = false;
	this.emailChecked = false;
	this.hookUp();
}

RegistrationForm.prototype = {
	hookUp: function () {
		this.form.getInputs('image','action')[0].observe('click',this.onSubmitHandler.bind(this));
	},
	
	onSubmitHandler: function (ev) {
		ev.stop();
		
		this.checkForValues();
		this.checkAgreed();
		this.checkPasswords();
		this.checkUsername();
		this.checkEmailAddress();
		this.checkValidStatus();
	},
	
	checkForValues: function () {
		this.form.getInputs('text').each(function (el) { if (!el.value) {this.errors.push('A text field is blank.');}}.bind(this));
	},
	
	checkAgreed: function () {
		if (!$('agreed').checked) {
			this.errors.push("You didn't agree to the terms of use.");
		}
	},
	
	checkPasswords: function () {
		var passwords = this.form.getInputs('password');
		
		if (passwords[0].value.strip() =='' || passwords[1].value.strip() =='') {
			this.errors.push("A password field is blank.");
		} else if (passwords[0].value != passwords[1].value) {
			this.errors.push("Your passwords don't match.");
		}
	},
	
	checkUsername: function () {
		var theUsername = this.form.getInputs('text','user[name]')[0].value;
		var usernamePattern = /^[a-z0-9._-]+$/;
		if (!usernamePattern.test(theUsername)) {
			this.errors.push("Your username is not valid.");
			this.usernameChecked=true;
		} else {
			new Ajax.Request('/newDev/app/ajax/validate/username/'+this.form.getInputs('text','user[name]')[0].value,
				{method: 'get', onSuccess: this.usernameCheckCallback.bind(this)}
			);
		}
	},
	
	checkEmailAddress: function () {
		var theEmail = this.form.getInputs('text','user[email]')[0].value;
		var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/; 
		if (!emailPattern.test(theEmail)) {
			this.errors.push("Your email address is invalid.");
			this.emailChecked=true;
		} else {
			new Ajax.Request('/newDev/app/ajax/validate/email/'+theEmail, {method: 'get', onSuccess: this.emailCheckCallback.bind(this)});
		}
	},
	
	usernameCheckCallback: function (transport) {
		this.usernameChecked = true;
		if (transport.responseText.strip()) {
			this.errors.push("The username you've chosen is already in use");
		}
	},
	
	emailCheckCallback: function (transport) {
		this.emailChecked = true;
		if (transport.responseText.strip()) {
			this.errors.push("The email you've entered is already being used.");
		}
	},
	
	checkValidStatus: function () {
		if (this.usernameChecked && this.emailChecked) {
			/** Validation is done **/
			this.errorContainer.innerHTML = '';
			if (this.errors.length) {
				/** there are errors **/
				this.resetChecked();
				this.showErrors();
			} else {
				this.form.request({onComplete: this.submitDone.bind(this)});
			}
			return true;
		}
		
		var validStatusChecker = this.checkValidStatus.bind(this);
		validStatusChecker.delay(0.2);
		return false;
	},
	
	resetChecked: function () {
		this.usernameChecked = false;
		this.emailChecked = false;
	},
	
	showErrors: function () {
		var errorList = new Element('ul');
		this.errors.length.times(function (index) {errorList.insert(new Element('li').update(this.errors[index]));}.bind(this));
		this.errorContainer.insert(errorList);
		
		this.errors = new Array();
	},
	
	submitDone: function (transport) {
		if (transport.responseText.blank()) {
			// ask them to confirm their email
			var container = this.form.parentNode;
			container.innerHTML = '';
			container.insert(
				new Element('p').update('An email has been sent to your email address.  Follow the link in the email to confirm your email address and complete registration.  Thanks you!')
			);
		} else {
			// could not save
			throw("Could not register user!");
		}
	}
}

if (registrationFormWidget) {delete registrationFormWidget;}
var registrationFormWidget = new RegistrationForm();