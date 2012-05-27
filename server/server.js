var io = require('socket.io').listen(1234);

/*

Game
{
  game_id : owner.id
  owner : Client,
  player : Client,
  name : String,
  word : String
}


Client
{
  user_id : io.socket id
  socket : io.Socket
  game : Game obj
  username : String
}

*/



// Current games array.
var games = new Array()

// Clients array
var clients = new Array()

// FIXTURES
games.push({
  name : "Game #1",
  owner_name : "Rouman",
  game_id : "asdf0q435hlkaf",
})

games.push({
  name : "Game #2",
  owner_name : "Rouman fake",
  game_id : "jalskfnzxk34",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

games.push({
  name : "Game #3",
  owner_name : "Wwhoooooot",
  game_id : "herpasdf",
})

// Helper function to get an id
function get_user(user_id) {
  for (i in clients) {
    if (clients[i].user_id == user_id) {
      return clients[i]
    }
  }
  console.log("ERROR: USER not found with id: ", user_id)
}

function get_game(game_id) {
  for (i in games) {
    if (games[i].game_id == game_id) {
      return games[i]
    }
  }
  console.log("ERROR: GAME not found with id: ", game_id)
}

function delete_game(game_id) {
  index = -1
  for (i in games) {
    if (games[i].game_id == game_id) {
      games.pop(i)
    }
  }
}

io.sockets.on('connection', function (socket) {

  console.log("Socket_id: " + socket.id)
  
  // We send the user_id, we don't store the user yet
  socket.emit('user_id', socket.id)

  socket.on('signup', function(data) {

    // The user sent his name, we save it now
    clients[data.user_id] = {
      username : data.username,
      user_id : data.user_id,
      user_socket : socket,
      /* no game yet */    
    }

    console.log(clients[data.user_id])
  })

  // Send the list of games
  socket.on('get_games', function() {
    // console.log("Somebody wants to know what games are out there!")
    var rc = new Array()

    for (i in games) {
      rc.push({
        name : games[i].name,
        owner_name : games[i].owner_name,
        game_id : games[i].game_id,
        word : games[i].word
      })
    }

    socket.emit("current_games", rc)
  })

  // Someone creates a game
  socket.on('create_game', function(data) {
    console.log("Creating game...")

    client = get_user(data.owner)

    game = {  
              game_id : client.user_id,
              owner : client,
              owner_name : client.username,
              name : data.name, 
              word : data.word
              /* no players yet */ 
            }

    games.push(game)

    client.game = game
  })

  // Owner quits game
  socket.on('end_game', function(data){

    game = get_game(data.game_id)

    if (game) {
      if (data.user_id == game.owner.user_id) {
        if (game.player) {
          io.sockets.socket(game.player.user_id).emit("endgame", { why: data.why})
        }
      } else if (data.user_id == game.player.user_id) {
        if (game.owner) {
          game.owner.user_socket.emit("endgame", { why : data.why })
        }
      }

      delete game.player.game
      delete game.owner.game
      delete_game(data.user_id)
    }
  })

  // Somebody joins a game
  socket.on('join_game', function(data) {
    console.log("Somebody wants to join...")

    if (!data.game_id) {
      console.log("ERROR: Trying to join a game without ID")
    } else {
      game = get_game(data.game_id)

      if (game) {
        user = get_user(data.user_id)

        game.player = user
        user.game = game

        owner_socket = io.sockets.socket(game.owner)

        owner_socket.emit("joined", { 
                                  username : data.username, 
                                  user_id : data.user_id
                                  })

        game.owner.user_socket.emit('game_start', { word : game.word })
        socket.emit('game_start', { word : game.word })
      } else {
        console.log("ERROR: That game doesn't exist")
        socket.emit("endgame", { why : "That game doesn't exist anymore" })
      } 
    }

  })

  // Paint action
  socket.on('draw', function(data) {
    console.log("Drawing!!")
    user = get_user(data.user_id)
    if (user) {
      game = user.game
      if (game) {
        game.player.user_socket.emit("draw", {
          x : data.x,

          y : data.y,
          brush : data.brush,
          r : data.r,
          g : data.g,
          b : data.b
        })
      } else {
        console.log("No game!")
      }  
    } else {
      console.log("That's no user!!")
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

  // Quit game
  socket.on('byebye', function(data) {
    console.log("Deleting session...")
    // Check if still in-game to notify
    delete clients[socket.id]
  })

});
