var express = require('express'),
  path = require('path'),
  app = express();

//set the port
app.set('port', 3000);


app.use(express.static(__dirname + '/public/static'));
app.use(express.static(__dirname + '/public'));
console.log('__dirname: '+__dirname);

app.get('/', function(req, res) {
  res.sendfile(__dirname + '/index.html');
});



// Listen for requests
var server = app.listen(app.get('port'), function () {
  console.log('The server is running on http://localhost:' + app.get('port'));
});

