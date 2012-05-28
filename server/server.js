var io = require('socket.io').listen(1234);

/*

Class definitions, just for reference

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
// This games won't work but are useful to test the Game List UI
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

// Helper function to get an user by id
function get_user(user_id) {
  for (i in clients) {
    if (clients[i].user_id == user_id) {
      return clients[i]
    }
  }
  console.log("ERROR: USER not found with id: ", user_id)
}

// Helper function to get a game by id
function get_game(game_id) {
  for (i in games) {
    if (games[i].game_id == game_id) {
      return games[i]
    }
  }
  console.log("ERROR: GAME not found with id: ", game_id)
}

// Helper function to delete an game by id
function delete_game(game_id) {
  index = -1
  for (i in games) {
    if (games[i].game_id == game_id) {
      games.pop(i)
    }
  }
}

// This fires on each connection and socket is the socket for each user
io.sockets.on('connection', function (socket) {

  console.log("Socket_id: " + socket.id)
  
  // We send the user_id, we don't store the user yet
  socket.emit('user_id', socket.id)

  // User signs up
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

    // Send only games without player
    for (i in games) {
      if (games[i].player == undefined) {
       rc.push({
        name : games[i].name,
        owner_name : games[i].owner_name,
        game_id : games[i].game_id,
        word : games[i].word
      })
     }
    }

    socket.emit("current_games", rc)
  })

  // Someone creates a game
  socket.on('create_game', function(data) {
    console.log("Creating game...")

    // Get the client
    client = get_user(data.owner)

    // Create object
    game = {  
              game_id : client.user_id,
              owner : client,
              owner_name : client.username,
              name : data.name, 
              word : data.word
              /* no players yet */ 
            }

    games[client.user_id] = game 

    // Save it
    // games.push(game)

    // Circular references!
    client.game = game
  })

  // Owner quits game
  socket.on('end_game', function(data){

    // Get the game
    game = get_game(data.game_id)

    // Check game exists
    if (game) {
      // Check who this is and emit properly
      if (data.user_id == game.owner.user_id) {
        if (game.player) {
          io.sockets.socket(game.player.user_id).emit("endgame", { why: data.why})
        }
      } else if (data.user_id == game.player.user_id) {
        if (game.owner) {
          game.owner.user_socket.emit("endgame", { why : data.why })
        }
      }

      // Cleaning up
      delete games[data.game_id]

    } else {
      console.log("NO SUCH GAME")
    }
  })

  // Somebody joins a game
  socket.on('join_game', function(data) {
    console.log("Somebody wants to join...")

    // Wrong message
    if (!data.game_id) {
      console.log("ERROR: Trying to join a game without ID")
    } else {
      // Get the game
      game = get_game(data.game_id)

      // Check if game exists
      if (game) {
        // Get the user object
        user = get_user(data.user_id)

        // Set circular values
        game.player = user
        user.game = game

        // Get the socket of the owner
        owner_socket = io.sockets.socket(game.owner)

        // Tell him somebody joined
        owner_socket.emit("joined", { 
                                  username : data.username, 
                                  user_id : data.user_id
                                  })

        // Start the game!!
        game.owner.user_socket.emit('game_start', { word : game.word })
        socket.emit('game_start', { word : game.word })
      } else {
        // Log error and send endgame message
        console.log("ERROR: That game doesn't exist")
        socket.emit("endgame", { why : "That game doesn't exist anymore" })
      } 
    }

  })

  // Paint action
  socket.on('draw', function(data) {
    console.log("Drawing!!")
    // Get user
    user = get_user(data.user_id)

    // Check if user exists
    if (user) {
      // Get the game
      game = user.game

      // Check game exists
      if (game) {
        // Send the player drawing information
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

  // Quit game
  socket.on('byebye', function(data) {
    console.log("Deleting session...")

    user = get_user(data.user_id)

    // Let's check if on a game yet
    if (user.game){
      game_id = user.game.game_id
      if (user.game.owner == user && user.game.player != undefined) {
        user.game.player.user_socket.emit("endgame", { why : "Server quit..."} )
      } else if (user.game.player != undefined && user.game.player == user) {
        user.game.owner.user_socket.emit("endgame", { why : "Player quit..."} )
      }

      delete_game(game_id)
    }

    // Check if still in-game to notify
    delete clients[socket.id]
  })

});
