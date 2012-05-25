var io = require('socket.io').listen(1234);

// Current games array.
var games = []

// Clients array
var clients = []


io.sockets.on('connection', function (socket) {

  console.log("Socket_id: " + socket.id)
  
  socket.emit('user_id', socket.id)

  socket.on('get_games', function() {
    // console.log("Somebody wants to know what games are out there!")
    socket.emit("current_games", games)
  })

  socket.on('send_message', function(data) {
    console.log(data)
  })

  socket.on('create_game', function(data) {
    console.log("Creating game...")
    // games.push( {
    //   owner : data.user_id,
    //   name : data.name
    // })
    game = { owner = data.user_id, name = data.name, word = "Dignity" }

  })

  socket.on('join_game', function(data) {
    if (game) {
      game.player = data.user_id
    }

    socket.send('start_game', )
  })

  socket.on('paint', function(data) {
    console.log('Painted at x:', data.x, ' y:', data.y)
  })

  socket.on('color', function(data) {
    console.log('Changed the color to: ', data)
  })

  socket.on('brush', function(data) {
    console.log('Changed the brush to: ', data.brush)
  })

  socket.on('message', function(data) {
    // WE shouldn't capture this I believe
    if (data.method) {

    } else {
      console.log("Discarding...(No method)")
    }

  })
});
