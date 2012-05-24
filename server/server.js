var io = require('socket.io').listen(1234);

var games = [ { name : 'game 1', mode : 'what' }, { name : 'game 2', mode : 'nweb' } ]

io.sockets.on('connection', function (socket) {

  socket.on('set nickname', function (name) {
    socket.set('nickname', name, function () {
      socket.emit('ready');
    });
  });

  socket.on('msg', function () {
    socket.get('nickname', function (err, name) {
      console.log('Chat message by ', name);
    });
  });

  socket.on('get_games', function() {
    console.log("Somebody wants to know what games are out there!")
    socket.emit("current_games", games)
  })
});
