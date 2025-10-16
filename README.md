# Blocky Game

_(Gamewerks corporation internal code—do not share!)_

## Credits

<<<<<<< HEAD
Primary developer: Mariia Minieieca, Suzune Yagi
=======
Primary developer: Suzune Yagi, Mariia Minieieva
>>>>>>> dcee6c2b22414c9f89072aeaf8a7801a20061801

### Resources Used
+ Random class documentation: https://docs.oracle.com/javase/8/docs/api/java/util/Random.html
+ Style Guide: https://osera.cs.grinnell.edu/ttap/data-structures-labs/style-guide.html
+ Object-oriented Modeling Reading: https://osera.cs.grinnell.edu/ttap/data-structures/object-oriented-modeling.html
+ Hint from Professor Osera to look into how the blocks are rendered to find an issue with upside down setup
+ Visual Studio Code 
+ Oracle, OpenJDK compiler

## Changelog

~~~console
Author: suzuneyagi <yagiryouyasushi@MacBookAir.grinnell.edu>
Date:   Mon Sep 22 20:22:14 2025 -0500

    Random block generation implemented: Inside Piece.java we implemented getNextPiece() which returns the piece in array PieceKind[] ALL or shuffles the array and returns its first element if we run out of shuffled elements. For Piece Object, we changed the kind field to this.kind = getNextPiece(); so that each new piece generated receives the random shape. 

commit f3836723346b3565183693c3c5a5241cbe62e4ed
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Mon Sep 22 19:53:54 2025 -0500

    Shuffle implemented: Inside PieceKind.java we implemented the shuffle() method that takes an array of PieceKind elements (forms) ieceKind[] ALL and shuffles them using Fisher-Yates Shuffle. 

commit 86c103c6a00fae770f32da4753b6f6c2f2abc274
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Mon Sep 22 19:32:21 2025 -0500

    This is the sixth error: We inverted the vertical axis of our rendering function paintComponent() to fix the directions the blocks fall (down instead of up). For two calls fillRect() we passed an inverted height/row parameter (second argument).

commit 26822b6efd4038908fff5807e31106d8bb770eba
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Sun Sep 21 16:32:07 2025 -0500

    This is the fifth error: Fixed the deletion of completed rows by passing the "row" (not the boolean value "well[row]") to the completeRows.add(row) call inside getCompletedRows() method inside Board.java

commit 2d468760f54404c0a2a170a888a2f647883fd0e8
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Sun Sep 21 16:22:05 2025 -0500

    This is the fourth error: We changed <= to < inside isValidPosition and now the program doesn't throw out of bound error and we can move left and right
    Initially in Board.java: When moving right the program was throwing "Index 10 out of bounds for length 10." We made the bounds strict (< instad of <= and > instad of >=) inside the isValidPosition() method. This allowed the piece to move back from right. 

commit 1f73213a3250b380f7055e73cebd02d4f4b47a31
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Sun Sep 21 14:53:55 2025 -0500

    This is the third error: we added processMovement() in the step() method to be able to move the block left and right
    Initially in BlockyGame.java: We saw that processMovement() was highlighting yellow, meaning it wasn't used anywhere.
    To allow the piece to move left and right, we added the processMovement() call to step().

commit 3da572076d585d3201ac3a3b1b336dee1b9d65b2
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Sun Sep 21 14:45:18 2025 -0500

    This is the second error: case RIGHT inside processMovement() method in BlockyGame.java was missing a break
    Initially in BlockyGame.java the case for movement RIGHT was missing a break. we added a break statement.

commit 17b98c7bae839c5adec148bd29271d173d8418ab
Author: Mariia <mariia.minieieva@gmail.com>
Date:   Sat Sep 20 21:11:36 2025 -0500

    This is the first error with readRotation column forloop out of bounds
    Initially in Loader.java: iterated through 5 columns while the rotation array only held 4 values. We change column bound to 4 so that the loop iterates only through 4 values.

commit dcee6c2b22414c9f89072aeaf8a7801a20061801
Author: suzuneyagi <yagiryouyasushi@MacBookAir.grinnell.edu>
Date:   Sat Sep 20 20:18:49 2025 -0500

    Added our names

commit afe5fb01903e400af8fa786f1a8c10dfa900169f (origin/mariia, mariia)
Author: Peter-Michael Osera <osera@cs.grinnell.edu>
Date:   Wed Feb 5 20:51:21 2025 -0600
~~~
