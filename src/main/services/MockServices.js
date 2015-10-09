var path = require('path');
var express = require('express');
var bodyParser = require('body-parser');
var logger = require('morgan');

var app = express();
app.use(logger('dev'));
app.use(bodyParser.json())

// Lets define a port we want to listen to
const PORT = 8082;

// Create routes
app.get('/mss/:level/:resource/:operation/check', function(req, res, next) {
	var requestedResource = req.params.resource;
	var requestedOperation = req.params.operation;
	var subscriptionLevel = req.params.level;

	if (subscriptionLevel.toLowerCase() === 'silver') {
		if (requestedResource.toLowerCase() === 'exportjobs') {
			if (requestedOperation.toLowerCase() === 'delete') {
				res.type('application/json').status(200).json({
					source : "Subscription check service",
					result : "Error",
					message : requestedOperation
							+ " on "
							+ requestedResource
							+ " not allowed for subscription level "
							+ subscriptionLevel
				});
			} else {
				res.type('application/json').status(200).json({
					source : "Subscription check service",
					result : "Success",
					message : "Allowed"
				});
			}
		}
	}
});

app.get('/mbs/users/:user', function(req, res, next) {
	var user = req.params.user;
	var status = Math.round(Math.random() * 100) > 30 ?
			(Math.round(Math.random() * 100) > 50 ? "good" : "bad") : "unknown";

	if (status === "unknown") {
		res.type('application/json');
		res.status(200).json({
			source : "Billing standing check service",
			result : "Error",
			message : "Unknown user"
		});
	} else if (status === "bad") {
		res.type('application/json');
		res.status(200).json({
			source : "Billing standing check service",
			result : "Error",
			message : "User does not have good standing"
		});
	} else {
		res.type('application/json');
		res.status(200).json({
			source : "Billing standing check service",
			result : "Success",
			message : "User has good standing"
		});
	}

});

app.get('/data/arpt', function(req, res) {
	var query = req.query;

	if (typeof query === "undefined") {
		res.type('application/json');
		res.status(400).json({
			source : "Airport data query service",
			result : "Error",
			message : "No query provided"
		});
	} else {
		res.type('application/json');
		res.status(200).json({
			result : "Success",
			source : "Airport data query service",
			message : "Query results metadata has been determined",
			information: {
				numRows : Math.round(Math.random() * 1000),
				fieldTypes : [
		        {
		            "fieldName" : "id",
		            "type" : "int"
		        },
		        {
		            "fieldName" : "city",
		            "type" : "char"
		        },
		        {
		            "fieldName" : "population",
		            "type" : "int"
		        },
		        {
		            "fieldName" : "density",
		            "type" : "double"
		        },
		        {
		            "fieldName" : "notes",
		            "type" : "varchar"
		        }
		    ]
			}
		});
	}
});

app.post('/pricing', function(req, res) {
	var body = req.body;
	var numRows = body.numRows;
	var fieldDataTypeMappings = body.fieldTypes;

	var totalPrice = 0.0;
	for (var j = 0; j < fieldDataTypeMappings.length; j++) {
		switch (fieldDataTypeMappings[j].type) {
			case 'char':
					totalPrice += numRows * 0.5;
				break;
			case 'varchar':
					totalPrice += numRows * 2.5;
				break;
			case 'int':
					totalPrice += numRows * 1;
				break;
			case 'float':
					totalPrice += numRows * 1.25;
				break;
			case 'double':
					totalPrice += numRows * 1.5;
				break;
			case 'boolean':
					totalPrice += numRows * 0.25;
				break;
			default:
					totalPrice += 0.0;
		}
	}

	res.type('application/json');
	res.status(200).json({
		source : "Pricing determination service",
		result : "Success",
		message : "Total price calculated",
		information : {
			price : totalPrice
		}
	});
});

// Fire it up!
app.listen(PORT);
console.log('Listening on port ' + PORT);
