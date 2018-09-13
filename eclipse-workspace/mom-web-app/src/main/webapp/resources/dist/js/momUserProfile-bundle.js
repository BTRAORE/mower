/******/ (function(modules) { // webpackBootstrap
/******/ 	// install a JSONP callback for chunk loading
/******/ 	function webpackJsonpCallback(data) {
/******/ 		var chunkIds = data[0];
/******/ 		var moreModules = data[1];
/******/ 		var executeModules = data[2];
/******/
/******/ 		// add "moreModules" to the modules object,
/******/ 		// then flag all "chunkIds" as loaded and fire callback
/******/ 		var moduleId, chunkId, i = 0, resolves = [];
/******/ 		for(;i < chunkIds.length; i++) {
/******/ 			chunkId = chunkIds[i];
/******/ 			if(installedChunks[chunkId]) {
/******/ 				resolves.push(installedChunks[chunkId][0]);
/******/ 			}
/******/ 			installedChunks[chunkId] = 0;
/******/ 		}
/******/ 		for(moduleId in moreModules) {
/******/ 			if(Object.prototype.hasOwnProperty.call(moreModules, moduleId)) {
/******/ 				modules[moduleId] = moreModules[moduleId];
/******/ 			}
/******/ 		}
/******/ 		if(parentJsonpFunction) parentJsonpFunction(data);
/******/
/******/ 		while(resolves.length) {
/******/ 			resolves.shift()();
/******/ 		}
/******/
/******/ 		// add entry modules from loaded chunk to deferred list
/******/ 		deferredModules.push.apply(deferredModules, executeModules || []);
/******/
/******/ 		// run deferred modules when all chunks ready
/******/ 		return checkDeferredModules();
/******/ 	};
/******/ 	function checkDeferredModules() {
/******/ 		var result;
/******/ 		for(var i = 0; i < deferredModules.length; i++) {
/******/ 			var deferredModule = deferredModules[i];
/******/ 			var fulfilled = true;
/******/ 			for(var j = 1; j < deferredModule.length; j++) {
/******/ 				var depId = deferredModule[j];
/******/ 				if(installedChunks[depId] !== 0) fulfilled = false;
/******/ 			}
/******/ 			if(fulfilled) {
/******/ 				deferredModules.splice(i--, 1);
/******/ 				result = __webpack_require__(__webpack_require__.s = deferredModule[0]);
/******/ 			}
/******/ 		}
/******/ 		return result;
/******/ 	}
/******/
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// object to store loaded and loading chunks
/******/ 	// undefined = chunk not loaded, null = chunk preloaded/prefetched
/******/ 	// Promise = chunk loading, 0 = chunk loaded
/******/ 	var installedChunks = {
/******/ 		"momUserProfile": 0
/******/ 	};
/******/
/******/ 	var deferredModules = [];
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId]) {
/******/ 			return installedModules[moduleId].exports;
/******/ 		}
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			i: moduleId,
/******/ 			l: false,
/******/ 			exports: {}
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.l = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// define getter function for harmony exports
/******/ 	__webpack_require__.d = function(exports, name, getter) {
/******/ 		if(!__webpack_require__.o(exports, name)) {
/******/ 			Object.defineProperty(exports, name, { enumerable: true, get: getter });
/******/ 		}
/******/ 	};
/******/
/******/ 	// define __esModule on exports
/******/ 	__webpack_require__.r = function(exports) {
/******/ 		if(typeof Symbol !== 'undefined' && Symbol.toStringTag) {
/******/ 			Object.defineProperty(exports, Symbol.toStringTag, { value: 'Module' });
/******/ 		}
/******/ 		Object.defineProperty(exports, '__esModule', { value: true });
/******/ 	};
/******/
/******/ 	// create a fake namespace object
/******/ 	// mode & 1: value is a module id, require it
/******/ 	// mode & 2: merge all properties of value into the ns
/******/ 	// mode & 4: return value when already ns object
/******/ 	// mode & 8|1: behave like require
/******/ 	__webpack_require__.t = function(value, mode) {
/******/ 		if(mode & 1) value = __webpack_require__(value);
/******/ 		if(mode & 8) return value;
/******/ 		if((mode & 4) && typeof value === 'object' && value && value.__esModule) return value;
/******/ 		var ns = Object.create(null);
/******/ 		__webpack_require__.r(ns);
/******/ 		Object.defineProperty(ns, 'default', { enumerable: true, value: value });
/******/ 		if(mode & 2 && typeof value != 'string') for(var key in value) __webpack_require__.d(ns, key, function(key) { return value[key]; }.bind(null, key));
/******/ 		return ns;
/******/ 	};
/******/
/******/ 	// getDefaultExport function for compatibility with non-harmony modules
/******/ 	__webpack_require__.n = function(module) {
/******/ 		var getter = module && module.__esModule ?
/******/ 			function getDefault() { return module['default']; } :
/******/ 			function getModuleExports() { return module; };
/******/ 		__webpack_require__.d(getter, 'a', getter);
/******/ 		return getter;
/******/ 	};
/******/
/******/ 	// Object.prototype.hasOwnProperty.call
/******/ 	__webpack_require__.o = function(object, property) { return Object.prototype.hasOwnProperty.call(object, property); };
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	var jsonpArray = window["webpackJsonp"] = window["webpackJsonp"] || [];
/******/ 	var oldJsonpFunction = jsonpArray.push.bind(jsonpArray);
/******/ 	jsonpArray.push = webpackJsonpCallback;
/******/ 	jsonpArray = jsonpArray.slice();
/******/ 	for(var i = 0; i < jsonpArray.length; i++) webpackJsonpCallback(jsonpArray[i]);
/******/ 	var parentJsonpFunction = oldJsonpFunction;
/******/
/******/
/******/ 	// add entry module to deferred list
/******/ 	deferredModules.push(["./src/js/mom-userprofile-app.js","vendor-app"]);
/******/ 	// run deferred modules when ready
/******/ 	return checkDeferredModules();
/******/ })
/************************************************************************/
/******/ ({

/***/ "./src/js/mom-userprofile-app.js":
/*!***************************************!*\
  !*** ./src/js/mom-userprofile-app.js ***!
  \***************************************/
/*! no exports provided */
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
eval("__webpack_require__.r(__webpack_exports__);\n/* harmony import */ var bootstrap__WEBPACK_IMPORTED_MODULE_0__ = __webpack_require__(/*! bootstrap */ \"./node_modules/bootstrap/dist/js/bootstrap.js\");\n/* harmony import */ var bootstrap__WEBPACK_IMPORTED_MODULE_0___default = /*#__PURE__*/__webpack_require__.n(bootstrap__WEBPACK_IMPORTED_MODULE_0__);\n/* harmony import */ var jquery__WEBPACK_IMPORTED_MODULE_1__ = __webpack_require__(/*! jquery */ \"./node_modules/jquery/dist/jquery.js\");\n/* harmony import */ var jquery__WEBPACK_IMPORTED_MODULE_1___default = /*#__PURE__*/__webpack_require__.n(jquery__WEBPACK_IMPORTED_MODULE_1__);\n/* harmony import */ var popper_js__WEBPACK_IMPORTED_MODULE_2__ = __webpack_require__(/*! popper.js */ \"./node_modules/popper.js/dist/esm/popper.js\");\n/* harmony import */ var _userProfile__WEBPACK_IMPORTED_MODULE_3__ = __webpack_require__(/*! ./userProfile */ \"./src/js/userProfile.js\");\n/* harmony import */ var _userProfile__WEBPACK_IMPORTED_MODULE_3___default = /*#__PURE__*/__webpack_require__.n(_userProfile__WEBPACK_IMPORTED_MODULE_3__);\n\n\n\n\n\n//# sourceURL=webpack:///./src/js/mom-userprofile-app.js?");

/***/ }),

/***/ "./src/js/userProfile.js":
/*!*******************************!*\
  !*** ./src/js/userProfile.js ***!
  \*******************************/
/*! no static exports found */
/***/ (function(module, exports, __webpack_require__) {

eval("/* WEBPACK VAR INJECTION */(function(jQuery, $) {function _defineProperty(obj, key, value) { if (key in obj) { Object.defineProperty(obj, key, { value: value, enumerable: true, configurable: true, writable: true }); } else { obj[key] = value; } return obj; }\n\n(function (removeClass) {\n  $(function () {\n    $('[data-toggle=\"tooltip\"]').tooltip();\n  });\n\n  jQuery.fn.removeClass = function (value) {\n    if (value && typeof value.test === \"function\") {\n      for (var i = 0, l = this.length; i < l; i++) {\n        var elem = this[i];\n\n        if (elem.nodeType === 1 && elem.className) {\n          var classNames = elem.className.split(/\\s+/);\n\n          for (var n = classNames.length; n--;) {\n            if (value.test(classNames[n])) {\n              classNames.splice(n, 1);\n            }\n          }\n\n          elem.className = jQuery.trim(classNames.join(\" \"));\n        }\n      }\n    } else {\n      removeClass.call(this, value);\n    }\n\n    return this;\n  };\n})(jQuery.fn.removeClass);\n\n$(document).ready(function () {\n  var _$$fileinput;\n\n  $('.selectpicker').selectpicker();\n  $(\"#input-44\").fileinput((_$$fileinput = {\n    previewFileType: \"image\",\n    theme: \"fa\",\n    //        uploadUrl: '/offer/image/upload',\n    showUpload: false,\n    // hide upload button\n    //        deleteUrl: \"/site/file-delete\",\n    //    \tdropZoneEnabled=true,\n    maxFileCount: 5,\n    initialPreviewShowDelete: true,\n    overwriteInitial: false,\n    language: \"fr\",\n    //        showRemove: true,\n    //        showUpload: false,\n    textEncoding: 'UTF-8',\n    showCaption: true,\n    showPreview: true,\n    showRemove: true,\n    //        showUpload: true,\n    showCancel: true,\n    showClose: true,\n    showUploadedThumbs: true,\n    initialPreviewAsData: true,\n    maxFilePreviewSize: 10240\n  }, _defineProperty(_$$fileinput, \"previewFileType\", \"image\"), _defineProperty(_$$fileinput, \"browseClass\", \"btn mom-default-btn\"), _defineProperty(_$$fileinput, \"browseLabel\", \"Choisir une image\"), _defineProperty(_$$fileinput, \"browseIcon\", \"<i class=\\\"fa fa-camera\\\"></i> \"), _defineProperty(_$$fileinput, \"removeClass\", \"btn btn-danger\"), _defineProperty(_$$fileinput, \"removeLabel\", \"Supprimer\"), _defineProperty(_$$fileinput, \"removeIcon\", \"<i class=\\\"fa fa-trash\\\"></i> \"), _$$fileinput));\n  var formd = new FormData();\n  $('#input-44').on('fileloaded', function (event, file, previewId, index, reader) {\n    var mainImgRadio = $(\"<div class=\\\"imgPrev\\\"><label><input type=\\\"radio\\\" class=\\\"mainImgRadio\\\" name=\\\"mainImgRadio\\\" /> Image principale </label><input type=\\\"hidden\\\" class=\\\"mainImgHiddenInput\\\" value=\\\"\" + file.name + \"\\\"/></div>\");\n    $(\"#\" + previewId).append(mainImgRadio);\n    $(\".imgPrev\").find(\"input.mainImgRadio\").on(\"change\", function () {\n      $(\"#mainImg\").val($(\".imgPrev input.mainImgHiddenInput\").val());\n    });\n    formd.app;\n    var filesInp = $(\"input#input-44[type='file']\");\n    var files = filesInp.prop(\"files\");\n    if (files.length > 1) files[f] = file;\n  });\n});\n/** offer edit checkboxes ***/\n\n$(function () {\n  $('.list-group.checked-list-box .list-group-item').each(function () {\n    // Settings\n    var $widget = $(this),\n        $checkbox = $('<input type=\"checkbox\" class=\"hidden\" hidden=\"hidden\" />'),\n        color = \"list-group-item-success\",\n        settings = {\n      on: {\n        icon: 'fa fa-check-square'\n      },\n      off: {\n        icon: 'fa fa-square'\n      }\n    };\n    $widget.css('cursor', 'pointer');\n    $widget.append($checkbox); // Event Handlers\n\n    $widget.on('click', function () {\n      $checkbox.prop('checked', !$checkbox.is(':checked'));\n      $checkbox.triggerHandler('change');\n      updateDisplay();\n    });\n    $checkbox.on('change', function () {\n      updateDisplay();\n    }); // Actions\n\n    function updateDisplay() {\n      var isChecked = $checkbox.is(':checked'); // Set the button's state\n\n      $widget.data('state', isChecked ? \"on\" : \"off\"); // Set the button's icon\n\n      $widget.find('.state-icon').removeClass().addClass('state-icon ' + settings[$widget.data('state')].icon); // Update the button's color\n\n      if (isChecked) {\n        $widget.addClass(color);\n      } else {\n        $widget.removeClass(color + ' active');\n      }\n    } // Initialization\n\n\n    function init() {\n      if ($widget.data('checked') == true) {\n        $checkbox.prop('checked', !$checkbox.is(':checked'));\n      }\n\n      updateDisplay(); // Inject the icon if applicable\n\n      if ($widget.find('.state-icon').length == 0) {\n        $widget.prepend('<span class=\"state-icon ' + settings[$widget.data('state')].icon + '\"></span>');\n      }\n    }\n\n    init();\n  });\n\n  if ($('input.autocomplete-address').val()) {\n    $('div.address-component').show();\n  } else {\n    $('div.address-component').hide();\n  }\n\n  $('input.autocomplete-address').blur(function () {\n    if ($(this).val()) {\n      $('div.address-component').show('slow');\n    } else {\n      $('div.address-component').hide('slow');\n    }\n  });\n  $(\"#oc-brand-search-input\").on(\"keyup\", function () {\n    var value = $(this).val().toLowerCase();\n    $(\"#oc-brand-list *\").filter(function () {\n      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);\n    });\n  }); //    filterable inputs\n\n  $('.filterable .btn-filter').click(function () {\n    var $panel = $(this).parents('.filterable'),\n        $filters = $panel.find('.filters input'),\n        $tbody = $panel.find('.table tbody');\n\n    if ($filters.prop('disabled') == true) {\n      $filters.prop('disabled', false);\n      $filters.first().focus();\n    } else {\n      $filters.val('').prop('disabled', true);\n      $tbody.find('.no-result').remove();\n      $tbody.find('tr').show();\n    }\n  });\n  $('.filterable .filters input').keyup(function (e) {\n    /* Ignore tab key */\n    var code = e.keyCode || e.which;\n    if (code == '9') return;\n    /* Useful DOM data and selectors */\n\n    var $input = $(this),\n        inputContent = $input.val().toLowerCase(),\n        $panel = $input.parents('.filterable'),\n        column = $panel.find('.filters th').index($input.parents('th')),\n        $table = $panel.find('.table'),\n        $rows = $table.find('tbody tr');\n    /* Dirtiest filter function ever ;) */\n\n    var $filteredRows = $rows.filter(function () {\n      var value = $(this).find('td').eq(column).text().toLowerCase();\n      return value.indexOf(inputContent) === -1;\n    });\n    /* Clean previous no-result if exist */\n\n    $table.find('tbody .no-result').remove();\n    /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */\n\n    $rows.show();\n    $filteredRows.hide();\n    /* Prepend no-result row if all rows are filtered */\n\n    if ($filteredRows.length === $rows.length) {\n      $table.find('tbody').prepend($('<tr class=\"no-result text-center\"><td colspan=\"' + $table.find('.filters th').length + '\">Aucun resultat trouvé</td></tr>'));\n    }\n  });\n  /** user image profile **/\n\n  var readURL = function readURL(input) {\n    if (input.files && input.files[0]) {\n      var reader = new FileReader();\n\n      reader.onload = function (e) {\n        $('.profile-pic').attr('src', e.target.result);\n      };\n\n      reader.readAsDataURL(input.files[0]);\n    }\n  };\n\n  $(\".file-upload\").on('change', function () {\n    readURL(this);\n  });\n  $(\".upload-button\").on('click', function () {\n    $(\".file-upload\").click();\n  });\n  /** End user profile **/\n});\n/* WEBPACK VAR INJECTION */}.call(this, __webpack_require__(/*! jquery */ \"./node_modules/jquery/dist/jquery.js\"), __webpack_require__(/*! jquery */ \"./node_modules/jquery/dist/jquery.js\")))\n\n//# sourceURL=webpack:///./src/js/userProfile.js?");

/***/ })

/******/ });