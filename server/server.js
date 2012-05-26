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
    game = {  owner : data.owner,
              owner_name : data.username,
              name : data.name, 
              word : data.word }
  })

  socket.on('end_game', function(data){ 

    if(game.player) {
        io.sockets.socket(game.player).emit("endgame")
    }
  })

  socket.on('join_game', function(data) {
    console.log("Somebody wants to join...")
    // Currently just one game
    if (game) {
      game.player = data.user_id
    }

    owner_socket = io.sockets.socket(game.owner)

    owner_socket.emit("joined", { 
                                username : data.username, 
                                user_id : data.user_id
                                })

    // socket.emit('start_game', "derp")
  })

  socket.on('paint', function(data) {
    console.log('Painted at x:', data.x, ' y:', data.y)
    if (game.player) {
      io.sockets.socket(game.player).emit("paint", {
        x : data.x,
        y : data.y,
        brush : data.brush,
        r : data.r,
        g : data.g,
        b : data.b
      })
    }
  })

  // Maybe I won't use these...
  socket.on('color', function(data) {
    console.log('Changed the color to: ', data)
  })

  socket.on('brush', function(data) {
    console.log('Changed the brush to: ', data.brush)
  })

  socket.on('guess', function(data) {
    // Maybe do this on the client?
    if (data.word.toLowerCase() == game.word.toLowerCase()) {

    }
  })

  socket.on('signup', function(data) {
    // This is 
    clients[data.user_id] = {
      username : data.username,
      user_id : data.user_id
    }
    console.log(clients)
    console.log(clients[data.user_id])
  })

  socket.on('byebye', function(data) {
    console.log("Deleting session...")
    // Check if still in-game to notify
    delete clients[socket.id]
  })

});
