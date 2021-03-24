package fighters;

import framework.BattleField;/*A rectangular grid in which battles take place.
 Each location in the grid may be empty, or may contain one of the following: 
 A red soldier, a blue soldier, or an obstacle.*/

public class BasicSoldier {/*this is the class. each soldier of the battlefield
is entitled to each of the methods of the class.*/
	
	/*these are the various instance variables*/
	public final BattleField grid;//The battlefield where this soldier fights.
	public int row, col;//The battlefield where this soldier fights.
	public int health;//The current health-level of this soldier
	public final int team;//Indicates which team this soldier is fighting on. 
	
	/*this it the copy constructor. it represents each particular soldier on the
	 * grid. it takes in 3 parameters. gridIn - the battle field grid. rowIn 
	 * colIn - current location, teamIn - the team of the soldier.
	 * it also sets the health variable to a default value.*/
	public BasicSoldier(BattleField gridIn, int teamIn, int rowIn, int colIn) {
		grid = gridIn;
		team = teamIn;
		row = rowIn;
		col = colIn;
		health = 10;
	}
	/*The four constants below represent the relative fighting attributes of all
	 *  soldiers of this class.  These attributes dictate what happens when a 
	 *  soldier of this class attacks someone or is attacked by someone*/
	public final static int INITIAL_HEALTH = 10;
	public final static int ARMOR = 20;
	public final static int STRENGTH = 30;
	public final static int SKILL = 40;
	
	/*these constants below will be used by your methods when they need to 
	 * specify a direction.*/
	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int UP_AND_RIGHT = 4;
	public final static int DOWN_AND_RIGHT = 5;
	public final static int DOWN_AND_LEFT = 6;
	public final static int UP_AND_LEFT = 7;
	public final static int NEUTRAL = -1;
	
	/*This method returns boolean (true or false) if the soldier can move.
	 * its purpose is to ensure that the soldier can move. 
	 * to check that, you would need to ensure that the grids above the 
	 * soldier, below the soldier, to the left of the soldier and to the right 
	 * of the soldier are all empty by calling grid.get*/
	public boolean canMove() {
		int above = grid.get(row - 1, col); /*to get the location above the 
		soldier*/
		int below = grid.get(row + 1, col); /* to get the location below the 
		soldier.*/
		int left = grid.get(row, col - 1);/*to get the location to the left of 
		 the soldier.*/
		int right = grid.get(row, col + 1);/*to get the location to the right 
		of the soldier*/
		if (above == BattleField.EMPTY || below == BattleField.EMPTY 
			|| left == BattleField.EMPTY|| right == BattleField.EMPTY) {
			/*The if statement checks if any of the variables - above, below,
			 * left, and right- are empty */
			return true;
		} else {
			return false;
		}
	}
	/*the purpose of this method is to count the number of enemies remaining.
	 * it return an integer called counter which i declared to be count
	 * the number of enemies left
	 * to be able  know which is the enemy or not, you need your team, and to 
	 * know that i used an if statement checks if my team is a particular color 
	 * team the enemy team is the other color.*/
	public int numberOfEnemiesRemaining() {
		int enemyTeam;
		if (team == BattleField.BLUE_TEAM) {
			enemyTeam = BattleField.RED_TEAM;
		} else {
			enemyTeam = BattleField.BLUE_TEAM;
		}
		int counter = 0; 
		/*the nested for loop goes through the grid and counts each enemy team
		 * at every point of the grid*/
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (grid.get(i, j) == enemyTeam) {
					counter++;
				}
			}
		}
		return counter;
	}
	
	/*the purpose of this method to determine the distance to a particular 
	 * point on the grid. (it calculates the number of moves it takes each 
	 * to reach the specified point by the parameters).
	 * the method takes in two parameters which refer to the row and column of
	 * the destination point. this method returns an Integer. */
	public int getDistance(int destinationRow, int destinationCol) {
		/*the math.abs is to ensure that the various distances are positive. its 
		 * is used because the distance between two places can't be negative.*/
		int row_distance = Math.abs(destinationRow - row);
		/*in order to calculate the their distances in row */
		int col_distance = Math.abs(destinationCol - col);
		/*to calculate their distances in columns*/
		
		/*the various if statements check if the destination points (destination 
		 * -Row and destinationCol) are equal, greater, or less than the 
		 * position of the giving soldier (row and col) */
		if (destinationRow > row && destinationCol == col) {
			return row_distance;
		} else if (destinationRow < row && destinationCol == col) {
			return row_distance;
		} else if (destinationRow == row && destinationCol < col) {
			return col_distance;
		} else if (destinationRow == row && destinationCol > col) {
			return col_distance;
		} else if (destinationRow < row && destinationCol > col) {
			return row_distance + col_distance;
		} else if (destinationRow > row && destinationCol > col) {
			return row_distance + col_distance;
		} else if (destinationRow < row && destinationCol < col) {
			return row_distance + col_distance;
		} else if (destinationRow > row && destinationCol < col) {
			return row_distance + col_distance;
		} else {
			return 0;
		}
	}
	/*the purpose of this method is to determine which kind of moves the soldier
	 * would take to reach another point on the grid. it returns the static
	 * methods that where declared integer above. the other points on the grid
	 * are the destinationRows and destinationCols */
	public int getDirection(int destinationRow, int destinationCol) {
		
		/*the various if statements check if the destination points (destination 
		 * -Row and destinationCol) are equal, greater, or less than the 
		 * position of the giving soldier (row and col) and returns the static 
		 * variables*/
		
		if (destinationRow > row && destinationCol == col) {
			return DOWN;
		} else if (destinationRow < row && destinationCol == col) {
			return UP;
		} else if (destinationRow == row && destinationCol < col) {
			return LEFT;
		} else if (destinationRow == row && destinationCol > col) {
			return RIGHT;
		} else if (destinationRow < row && destinationCol > col) {
			return UP_AND_RIGHT;
		} else if (destinationRow > row && destinationCol > col) {
			return DOWN_AND_RIGHT;
		} else if (destinationRow < row && destinationCol < col) {
			return UP_AND_LEFT;
		} else if (destinationRow > row && destinationCol < col) {
			return DOWN_AND_LEFT;
		} else {
			return NEUTRAL;
		}
	}
	
	/*the purpose of this method is to check the direction of the nearest
	 * friend. it calls upon the direction method, which is used to get the 
	 * direction to a particular point on the grid. This method returns 
	 * direction, which calls upon the get direction method. it also calls upon
	 * the get distance method to know its distance away from all his team mates
	 *  before comparing them to get the nearest. */
	public int getDirectionOfNearestFriend() {
		
		int nearest = 1000000000;/*the variable nearest was declared to the 
		number 1000000000 based on the assumption that there can't be a soldier
		on the grid with such distance away.  */
		int direction = 0; /*direction is declared 0 because its an integer*/
		
		/*the nested for loop check every location on the grid for a team mate
		 * and checks if its less that nearest.*/
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (grid.get(i, j) == team && (i != row || j != col)) {
					if (getDistance(i, j) < nearest) {
						nearest = getDistance(i, j);
						direction = getDirection(i, j);
					}
				}
			}
		}
		return direction;
	}
	
	/*the purpose of this method is to count the number of near by friend in a
	 * giving radius. it calls on the get distance method to ensure that you 
	 * the friends distance is within the radius. it also returns an integer.*/
	public int countNearbyFriends(int radius) {
		
		int counter = 0;/*the variable is used to count the number of friends in
		 within the radius.it increments by one for every team mate within the
		 radius*/
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (grid.get(i, j) == team && (i != row || j != col)) {
					if (getDistance(i, j) <= radius) {
						counter++;
					}
				}
			}
		}
		return counter;
	}
	
	/*the purpose of this method is to get the direction of the nearest enemy.
	 * it returns the direction of the enemy if the enemy is within the radius
	 * and returns neutral if the enemy is not within the radius. 
	 *  to be able  know which is the enemy or not, you need your team, and to 
	 * know that i used an if statement checks if my team is a particular color 
	 * team the enemy team is the other color.
	 * this method calls upon the methods getDistance-to ensure its within the 
	 * radius- and getDirection-to be able to get the direction of the enemy.*/
	public int getDirectionOfNearestEnemy(int radius) {
		int enemyTeam;
		if (team == BattleField.BLUE_TEAM) {
			enemyTeam = BattleField.RED_TEAM;
		} else {
			enemyTeam = BattleField.BLUE_TEAM;
		}
		int nearest = 1000000000;/*the integer variable nearest was declared to 
		the number 1000000000 based on the assumption that there can't be a 
		soldier on the grid with such distance away. so far it will change when
		discovering that there is a closer team mate. */
		int direction = 0;//direction is declared 0 because its an integer.
		
		/*the nested for loop check every location on the grid for an enemy
		 * and checks if its less that nearest*/
		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {
				if (grid.get(i, j) == enemyTeam) {
					if (getDistance(i, j) < nearest) {
						nearest = getDistance(i, j);
						direction = getDirection(i, j);
					}
				}
			}
		}
		if (nearest <= radius) {
			return direction;
		} else {
			return NEUTRAL;
		}
		/*if while iterating through the grid within the radius, no enemy was 
		 * found, it would return neutral, else it would return the integer 
		 * representing the direction to the nearest enemy.
		 */
	}
	
	/*this method is the heart of the soldier. its purpose is that it is called
	 * by the frame work each time the soldier turns to perform an action. the 
	 * 3 actions are move, attack or do nothing. knowing the soldier can only
	 * perform one action at a time, all actions are put in if statements. */
	public void performMyTurn() {
		int enemyTeam;/*to be able  know which is the enemy or not, you need to
		know your team, and to know that, i used an if statement checks if my 
		team is a particular color team the enemy team is the other color. */
		if (team == BattleField.BLUE_TEAM) {
			enemyTeam = BattleField.RED_TEAM;
		}else {
			enemyTeam = BattleField.BLUE_TEAM;
		}
		int above = grid.get(row-1,col);/*to get the location on the grid above 
		the soldier.*/
		int below = grid.get(row+1, col);/*to get the location on the grid
		below the soldier.*/
		int left = grid.get(row, col-1);/*to get the location on the grid to the
		left of the soldier.*/
		int right= grid.get(row, col+1);/*to get the location of the grid to the 
		right of the soldier.*/
		
		/*these if statements check if an enemy is above, below,left or right of
		 * the soldier to attack, knowing that you can only attack an enemy if
		 * its directly above, below or beside. */
		if (above == enemyTeam) {
			grid.attack(row-1,col);
		}else if (below == enemyTeam) {
			grid.attack(row+1,col);
		}else if (right== enemyTeam) {
			grid.attack(row, col + 1);
		}else if (left == enemyTeam) {
			grid.attack(row,col-1);
		
		/*these if statements check if above, below or beside the soldier are 
		 * empty, if so the soldier should move any of the empty spaces.*/
		}else if(above == BattleField.EMPTY) {
			row--;
		}else if(below == BattleField.EMPTY) {
			row ++;
		}else if (left == BattleField.EMPTY) {
			col --;
		}else if (right == BattleField.EMPTY) {
			col ++;
		
		/*these if statements check for edges. they check to make sure that the
		 *  soldier moves to an empty space when it approaches the end of the 
		 * grid. it basically ensures that the soldier does not go out of 
		 * bound.*/
		}else if ((below == BattleField.OUT_OF_BOUNDS || 
				right == BattleField.OUT_OF_BOUNDS || 
				above == BattleField.OUT_OF_BOUNDS)  && 
				left == BattleField.EMPTY) {
			col--;
		}else if ((below == BattleField.OUT_OF_BOUNDS || 
				right==BattleField.OUT_OF_BOUNDS || 
				left == BattleField.OUT_OF_BOUNDS) && 
				above == BattleField.EMPTY) {
			row--;
		}else if ((below == BattleField.OUT_OF_BOUNDS || 
				left == BattleField.OUT_OF_BOUNDS || 
				above == BattleField.OUT_OF_BOUNDS) && 
				right == BattleField.EMPTY) {
			col++;
		}else if ((above == BattleField.OUT_OF_BOUNDS || 
				right == BattleField.OUT_OF_BOUNDS || 
				left == BattleField.OUT_OF_BOUNDS) && 
				below == BattleField.EMPTY){
			row++;
		}
	
	}

}
