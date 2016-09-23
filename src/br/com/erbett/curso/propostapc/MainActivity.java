/* 
 * Copyright 2016 Erbett H. R. Oliveira, Inc. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 * 		
 * 		1. Redistributions of source code must retain the above copyright notice, this list of
 * 			conditions and the following disclaimer.
 * 
 * 		2. Redistributions in binary form must reproduce the above copyright notice, this list
 * 		   of conditions and the following disclaimer in the documentation and/or other materials
 *         provided with the distribution.
 *         
 * THIS SOFTWARE IS PROVIDED BY ERBETT HINTON RIBEIRO OLIVEIRA, INC. ''AS IS'' AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, AND LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ERBETT HINTON RIBEIRO OLIVEIRA, INC. OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 */
package br.com.erbett.curso.propostapc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import br.com.erbett.curso.propostapc.task.Task;

public class MainActivity extends Activity implements OnClickListener, MainActivityListener{
	
	private static final String INSTALAR = "Instalar";
	private static final String NAO_INSTALAR = "Nao Instalar";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Button button = (Button) findViewById(R.id.button_iniciar);
		button.setOnClickListener(this);
	}


	@Override
	public void onClick(View view) {
		if (view.getId() == R.id.button_iniciar){
			new Task(this).execute();
		}
	}
	
	
	public String getSistemaOperacional(){
		RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_sistema_operacinal);
		int selectedId = radioGroup.getCheckedRadioButtonId();
		RadioButton radioButton = (RadioButton) findViewById(selectedId);
		String string = "Nenhum";
		
		if (radioButton != null){
			string = radioButton.getText().toString();
		}
				
		return string;
	}
	
	
	public String getSoftwares(){
		
		CheckBox antivirus = (CheckBox) findViewById(R.id.check_box_antivirus);
		CheckBox navegadorWeb = (CheckBox) findViewById(R.id.check_box_navegador_web);
		CheckBox office = (CheckBox) findViewById(R.id.check_box_office);
		CheckBox skype = (CheckBox) findViewById(R.id.check_box_skipe);
		CheckBox viber = (CheckBox) findViewById(R.id.check_box_viber);
		
		StringBuffer result = new StringBuffer();
		result.append("AntiVirus: ").append(getString(antivirus));
		result.append("\nNavegador Web: ").append(getString(navegadorWeb));
		result.append("\nOffice 2010: ").append(getString(office));
		result.append("\nSkype: ").append(getString(skype));
		result.append("\nViber: ").append(getString(viber));
		
		return result.toString();
	}
	
	
	private String getString(CheckBox checkBox){
		return (checkBox.isChecked())? INSTALAR: NAO_INSTALAR;
	}


	@Override
	public void resultadoTask(String string) {
		Intent intent = new Intent(this, PedidoActivity.class);		
		intent.putExtra("pedido", string);
		startActivity(intent);
	}
	
	
	
}
