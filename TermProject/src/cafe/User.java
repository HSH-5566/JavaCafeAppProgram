package cafe;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

import mgr.Manageable;

public class User implements Manageable {
	public String id;
	public String pw;
	public String name;

	@Override
	// 0 Customer1 4682 허수빈 end
	public void read(Scanner scan) {
		id = scan.next();
		pw = scan.next();
		name = scan.next();
	}

	@Override
	// 아이디 : Customer1, 이름 : 허수빈
	public void print() {
		System.out.printf("아이디 : %s  이름 : %s\n", id, name);
	}

	@Override
	public boolean matches(String kwd) {
		if (id.contentEquals(kwd))
			return true;
		return false;
	}

	@Override
	public void writeToFile(BufferedWriter bw) throws IOException {
		
	}

}
