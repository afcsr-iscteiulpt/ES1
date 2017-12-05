package Bruno;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ListModel;

import Alberto.GUI;
import Daniel.Avaliador;
import antiSpamFilter.AntiSpamFilterAutomaticConfiguration;
 
public class Cliente {

	private static File_Scanner scanner = new File_Scanner();
	private static AntiSpamFilterAutomaticConfiguration a = new AntiSpamFilterAutomaticConfiguration();
	private static Avaliador avaliador = new Avaliador();;
	
	private GUI gui = new GUI(this);
	private static ArrayList<Rule> rules_cf;
	private static ArrayList<String[]> spam;
	private static ArrayList<String[]> ham;
	
	private static int falsos_pos;
	private static int falsos_neg;
	
	public void get_rules_list() throws FileNotFoundException{
		rules_cf = scanner.Scan_Rules_cf(gui.getRulespath());
	}
	
	public void get_ham_list() throws FileNotFoundException{
		ham = scanner.Scan_Spam_or_Ham(gui.getHampath());
	}
	
	public void get_spam_list() throws FileNotFoundException{
		spam = scanner.Scan_Spam_or_Ham(gui.getSpampath());
	}
	
	public void rules_cf_to_Jlist() throws FileNotFoundException{
		for(int i = 0; i < rules_cf.size(); i++ ){
			gui.model1.addElement(rules_cf.get(i).getName());
		}
	}
	
	public void display_peso(ArrayList<Rule> list, int index){
//		System.out.println(index);
//		gui.setTextField_7("" + rules_cf.get(gui.getIndex()).getValor());
		gui.setTextField_7("" + list.get(index).getValor());
	}
	
	public void change_peso(int index, double valor){
		rules_cf.get(index).setValor(valor);
	}
	
	public void start_Avaliador(){
		avaliador.replaceFields(rules_cf, spam, true);
		falsos_pos = avaliador.avaliar();
		
		avaliador.replaceFields(rules_cf, ham, false);
		falsos_neg = avaliador.avaliar();
		
		gui.setTextField_3("" + falsos_pos);
		gui.setTextField_4("" + falsos_neg);
		
	}
	
	public static void main(String[] arg) throws IOException  {
		Cliente c = new Cliente();	
	}
	
	public ArrayList<Rule> getRules_cf(){
		return rules_cf;
	}
	
	public ArrayList<String[]> getHam(){
		return ham;
	}
	
	public ArrayList<String[]> getSpam(){
		return spam;
	}
	
}
