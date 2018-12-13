package com.biz.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.bank.vo.BankVO;

public class BankService {
	
	List<BankVO> bankList;
	String strFileName;
	
	public BankService(String strFileName) {
		bankList = new ArrayList();
		this.strFileName = strFileName;
	}
	
	public void readFile() {
		FileReader fr;
		BufferedReader buffer;
		
		try {
			
			fr = new FileReader(strFileName);
			buffer = new BufferedReader(fr);
			
			while(true) {
				String strLine = buffer.readLine();
				if(strLine == null) break;
				
				String[] strSpl = strLine.split(":");
				
				System.out.print(strSpl[0] + "-");
				System.out.print(strSpl[1] + "-");
				System.out.print(strSpl[2] + "\n");
				
				BankVO vo = new BankVO();
				
				vo.setStrId(strSpl[0]);
				vo.setIntBalance(Integer.valueOf(strSpl[1]));
				vo.setStrLastDate(strSpl[2]);
				
				bankList.add(vo);
			}
			buffer.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void bankInput() {
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("계좌번호");
		String strAccount = scan.nextLine();
		BankVO b = findId(strAccount);
		
		if(b == null) {
			
			System.out.println("계좌번호 없음");
			return ;
		}
		
		int iB = b.getIntBalance();
		System.out.print("입금액 >>");
	    String strB = scan.nextLine();
	    int intB = Integer.valueOf(strB);
	    
	    int lB = iB + intB;
	    b.setIntBalance(lB);
	    
	    System.out.println("입금완료");
	    
	    System.out.print("현재 잔고 : " + b.getIntBalance());
	    
	   
		
	}
	
	public BankVO findId(String strId) {
		
		int intSz = bankList.size();
		boolean bool = false;
		for(int i = 0 ; i < intSz ; i++) {
			if(strId.equals(bankList.get(i).getStrId()) ) {
				bool = true;
				return bankList.get(i);
//				System.out.println(bankList.get(i));
			}
		}
		if(bool = false) {
			System.out.println("등록된 계좌가 없습니다");
		}
		return null;
		
	}
	


}
