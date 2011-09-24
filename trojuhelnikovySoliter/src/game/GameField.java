package game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GameField {

	public GameField(int n) {
		super();
		this.gameField = new int [n][];
		this.n = n;
		this.followers = new HashSet<GameField>();
		this.m = (n+1)*(n+1)-3;
		Random random = new Random();
		int tmp  = random.nextInt(m-n);
		this.q = tmp==0?1:tmp;
		tmp  = random.nextInt(5);
		this.x = tmp==0?1:tmp;
	}

	private final int n;
	
	private final int m;
	
	private final int q;
	
	private final int x;
	
	private int [] [] gameField;
	
	private Set<GameField> followers;
	
	public void initGameField()
	{
		if(this.n > 1)
		{
			for(int i = 0; i < n ; i++)
			{
				if(i == n - 1)
					this.gameField [i] = new int [2*(i+1) - 1];
				else
					this.gameField [i] = new int [2*(i+1) + 1];
			}
		}
	}
	
	public void generateGameField()
	{
		Random random = new Random();
		List<Coordination> coordinations = new ArrayList<GameField.Coordination>();
		for(int i=0; i< (x+q+1); i++)
		{
			boolean endLoop = false;
			while(!endLoop)
			{
				int row = random.nextInt(this.gameField.length);
				//row = row==0?1:row;
				int column = random.nextInt(this.gameField[row].length);
				Coordination coordination = new Coordination(row, column);
				if(!coordinations.contains(coordination))
				{
					this.gameField[row][column]=1;
					coordinations.add(coordination);
					endLoop = true;
				}
			}
		}
		doBacktrackMove();
		/*for(int i=0; i< this.gameField.length; i++)
		{
			for(int j=0; i < this.gameField[i].length; j++)
			{
				
			}
		}*/
	}
	private void doBacktrackMove()
	{
		Random random = new Random();
		for(int i=0; i< this.q; i++)
		{
			int row = random.nextInt(this.gameField.length);
			//row = row==0?1:row;
			int column = random.nextInt(this.gameField[row].length);
			if(this.gameField[row][column]==1)
			{
				//okrajova pole, 4 stupne volnosti
				if((row!=this.n-1 && (column==0 || column==2*(row+1) + 1))
						||(row==this.n-1)||(row!=this.n-1 && (column==1 || column==2*(row+1))))
				{
					//posledni radek
					if(row==this.n-1)
					{
						//neni krajni pozice
						if(column!=0 && column!=2*(row+1) + 1)
						{
							//neni jedna pred prvni krajni pozici
							if(column!=1)
							{
								//pohyb vlevo
								if(this.gameField[row][column-1]==0 && this.gameField[row][column-2]==0)
								{
									this.gameField[row][column-1]=1;
									this.gameField[row][column-2]=1;
									this.gameField[row][column]=0;
								}
							}
							//neni jedna pred posledni krajni pozici
							else if(column!=2*(row+1))
							{
								//pohyb vpravo
								if(this.gameField[row][column+1]==0 && this.gameField[row][column+2]==0)
								{
									this.gameField[row][column+1]=1;
									this.gameField[row][column+2]=1;
									this.gameField[row][column]=0;
								}
							}
							if(column%2==0)
							{
								//pohyb vlevo nahoru
								if(this.gameField[row][column-1]==0 && this.gameField[row-1][column]==0)
								{
									this.gameField[row][column-1]=1;
									this.gameField[row-1][column]=1;
									this.gameField[row][column]=0;
								}
								//pohyb vpravo nahoru
								else if(this.gameField[row][column]==0 && this.gameField[row-1][column+1]==0)
								{
									this.gameField[row][column]=1;
									this.gameField[row-1][column+1]=1;
									this.gameField[row][column]=0;
								}
							}
						}
						//je krajni pozice zleva
						else if(column==0)
						{
							//pohyb vpravo
							if(this.gameField[row][column+1]==0 && this.gameField[row][column+2]==0)
							{
								this.gameField[row][column+1]=1;
								this.gameField[row][column+2]=1;
								this.gameField[row][column]=0;
							}
							//pohyb vpravo nahoru
							else if(this.gameField[row][column]==0 && this.gameField[row-1][column+1]==0)
							{
								this.gameField[row][column]=1;
								this.gameField[row-1][column+1]=1;
								this.gameField[row][column]=0;
							}
						}
						//je krajni pozice zprava
						else if(column!=2*(row+1) + 1)
						{
							//pohyb vlevo nahoru
							if(this.gameField[row-1][column]==0 && this.gameField[row-1][column-1]==0)
							{
								this.gameField[row-1][column]=1;
								this.gameField[row-1][column-1]=1;
								this.gameField[row][column]=0;
							}
							//pohyb vlevo
							else if(this.gameField[row][column-1]==0 && this.gameField[row][column-2]==0)
							{
								this.gameField[row][column-1]=1;
								this.gameField[row][column-2]=1;
								this.gameField[row][column]=0;
							}
						}
					}
					//TODO chybi okrajove prvky krome posledniho radku!!!!
				}
				//vnitrni pole maji vsech 6 stupnu volnosti
				else
				{
					if(column%2==0)
					{
						//pohyb vpravo nahoru ve stejnem sloupci
						if(this.gameField[row][column+1]==0 && this.gameField[row-1][column]==0)
						{
							this.gameField[row][column+1]=1;
							this.gameField[row-1][column]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vlevo dolu ve stejnem sloupci, pokud neni v predposlednim radku
						else if(row!=n-2 && this.gameField[row+1][column+1]==0 && this.gameField[row+1][column]==0)
						{
							this.gameField[row+1][column+1]=1;
							this.gameField[row+1][column]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vlevo dolu ve stejnem sloupci, pokud je v predposlednim radku
						else if(row==n-2 && this.gameField[row+1][column]==0 && this.gameField[row+1][column-1]==0)
						{
							this.gameField[row+1][column]=1;
							this.gameField[row+1][column-1]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vlevo nahoru v jinem sloupci
						else if(this.gameField[row][column-1]==0 && this.gameField[row-1][column-2]==0)
						{
							this.gameField[row][column-1]=1;
							this.gameField[row-1][column-2]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vpravo dolu v jinem sloupci, pokud neni v predposlednim radku
						else if(row!=n-2 && this.gameField[row+1][column+1]==0 && this.gameField[row+1][column+2]==0)
						{
							this.gameField[row+1][column+1]=1;
							this.gameField[row+1][column+2]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vpravo dolu v jinem sloupci, pokud je v predposlednim radku
						else if(row==n-2 && this.gameField[row+1][column]==0 && this.gameField[row+1][column+1]==0)
						{
							this.gameField[row+1][column]=1;
							this.gameField[row+1][column+1]=1;
							this.gameField[row][column]=0;
						}
						/*//pohyb ve stejnem radku doleva
						else if(this.gameField[row][column-1]==0 && this.gameField[row][column-2]==0)
						{
							this.gameField[row][column-1]=1;
							this.gameField[row][column-2]=1;
							this.gameField[row][column]=0;
						}
						//pohyb ve stejnem radku doprava
						else if(this.gameField[row][column+1]==0 && this.gameField[row][column+2]==0)
						{
							this.gameField[row][column+1]=1;
							this.gameField[row][column+2]=1;
							this.gameField[row][column]=0;
						}*/
						//stejne
					}
					else
					{
						//pohyb vpravo nahoru v jinem sloupci
						if(this.gameField[row-1][column-1]==0 && this.gameField[row-1][column]==0)
						{
							this.gameField[row-1][column-1]=1;
							this.gameField[row-1][column]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vlevo dolu ve jinem sloupci, pokud neni v predposlednim radku
						else if(row!=n-2 && this.gameField[row][column-1]==0 && this.gameField[row+1][column]==0)
						{
							this.gameField[row][column-1]=1;
							this.gameField[row+1][column]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vlevo dolu v jinem sloupci, pokud je v predposlednim radku
						else if(row==n-2 && this.gameField[row][column-1]==0 && this.gameField[row+1][column-1]==0)
						{
							this.gameField[row][column-1]=1;
							this.gameField[row+1][column-1]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vlevo nahoru v jinem sloupci
						else if(this.gameField[row-1][column-1]==0 && this.gameField[row-1][column-2]==0)
						{
							this.gameField[row-1][column-1]=1;
							this.gameField[row-1][column-2]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vpravo dolu ve stejnem sloupci, pokud neni v predposlednim radku
						else if(row!=n-2 && this.gameField[row][column+1]==0 && this.gameField[row+1][column+2]==0)
						{
							this.gameField[row][column+1]=1;
							this.gameField[row+1][column+2]=1;
							this.gameField[row][column]=0;
						}
						//pohyb vpravo dolu ve stejnem sloupci, pokud je v predposlednim radku
						else if(row==n-2 && this.gameField[row][column+1]==0 && this.gameField[row+1][column+1]==0)
						{
							this.gameField[row][column+1]=1;
							this.gameField[row+1][column+1]=1;
							this.gameField[row][column]=0;
						}
						
					}
					//pohyb ve stejnem radku doleva
					if(this.gameField[row][column-1]==0 && this.gameField[row][column-2]==0)
					{
						this.gameField[row][column-1]=1;
						this.gameField[row][column-2]=1;
						this.gameField[row][column]=0;
					}
					//pohyb ve stejnem radku doprava
					else if(this.gameField[row][column+1]==0 && this.gameField[row][column+2]==0)
					{
						this.gameField[row][column+1]=1;
						this.gameField[row][column+2]=1;
						this.gameField[row][column]=0;
					}
				}//konec vetve pro 6 stupnu volnosti
			}
		}
	}
	
	
	
	public int[][] getGameField() {
		return gameField;
	}

	public void setGameField(int[][] gameField) {
		this.gameField = gameField;
	}

	public Set<GameField> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<GameField> followers) {
		this.followers = followers;
	}



	private class Coordination
	{
		
		public Coordination(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		private int x;
		private int y;
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
		@Override
		public boolean equals(Object o)
		{
			if(o==null) return false;
			Coordination c = (Coordination)o;
			if(this.getX()==c.getX() && this.getY()==c.getY()) return true;
			return false;
		}
	}
}
