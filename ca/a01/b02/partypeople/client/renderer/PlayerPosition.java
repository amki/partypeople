package ca.a01.b02.partypeople.client.renderer;

public class PlayerPosition {
	public String name;
	public int x,y,z;
	
	public PlayerPosition(String name, int x, int y, int z) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	PlayerPosition(PlayerPosition pos) {
		this.name = pos.name;
		this.x = pos.x;
		this.y = pos.y;
		this.z = pos.z;
	}

}
