package br.senai.sp.cotia.folhasalarial;



import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


// ALUNOS: Anabelle Silva Tiberowski e Rafael Caroba de Souza
public class MainActivity extends AppCompatActivity {
    private TextView TvsalBruto, TvDependentes, TvPlanSaude, TvVTransp, TvVRef, TvVAlim;
    private EditText editSalBruto, editDependentes;
    private Spinner spinnerPlano;
    private RadioGroup rgVTransp, rgVRef, rgVAlim;
//    private RadioButton rbSimVT, rbSimVR, rbsimVA, rbNaoVT, rbNaoVR, rbNaoVA;
    private Button btCalcular, btLimpar;

    private static double salBruto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TvsalBruto = findViewById(R.id.tv_salrio_bruto);
        TvDependentes = findViewById(R.id.tv_dependentes);
        TvPlanSaude = findViewById(R.id.tv_planSaude);
        TvVTransp = findViewById(R.id.tv_VT);
        TvVRef = findViewById(R.id.tv_VR);
        TvVAlim = findViewById(R.id.tv_VA);

        editSalBruto = findViewById(R.id.edit_salBruto);
        editDependentes = findViewById(R.id.edit_dependentes);

        spinnerPlano = findViewById(R.id.spinner_plano);

        rgVTransp = findViewById(R.id.rg_VT);
        rgVRef = findViewById(R.id.rg_VR);
        rgVAlim = findViewById(R.id.rg_VA);

//        rbSimVT = findViewById(R.id.rb_simVT);
//        rbNaoVT = findViewById(R.id.rb_naoVT);
//        rbSimVR = findViewById(R.id.rb_simVR);
//        rbNaoVR = findViewById(R.id.rb_naoVR);
//        rbsimVA = findViewById(R.id.rb_simVA);
//        rbNaoVA = findViewById(R.id.rb_naoVA);

        btCalcular = findViewById(R.id.bt_calcular);
        btLimpar = findViewById(R.id.bt_limpar);

        btCalcular.setOnClickListener(v -> {

//            Log.w(TAG, "onCreate: radiobutton", rgVTransp.getCheckedRadioButtonId());

                    // validação dos campos
                    if (editSalBruto.getText().toString().isEmpty()) {
                        editSalBruto.setError(getString(R.string.valida_salBruto));
                        Toast.makeText(getBaseContext(), R.string.valida_salBruto, Toast.LENGTH_SHORT).show();
                    } else if (editDependentes.getText().toString().isEmpty()) {
                        editDependentes.setError(getString(R.string.valida_depend));
//                Snackbar.make(editAltura, R.string.valida_altura, Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(getBaseContext(), R.string.valida_depend, Toast.LENGTH_SHORT).show();
                    } else if (rgVTransp.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getBaseContext(), R.string.valida_VT, Toast.LENGTH_SHORT).show();
                    } else if (rgVAlim.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getBaseContext(), R.string.valida_VA, Toast.LENGTH_SHORT).show();
                    } else if (rgVRef.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(getBaseContext(), R.string.valida_VR, Toast.LENGTH_SHORT).show();
                    } else{

                        double sb;
                        int numDep;
                        numDep = Integer.parseInt(editDependentes.getText().toString());
                        sb = Double.parseDouble(editSalBruto.getText().toString());


                    }


                    //               switch (editSalBruto.getText().toString();){
//                    case: <= 3000) {
//                            ps = 60.0;
//                        } else {
//                            ps = 80.0;
//                        }
//                        break;
//
//                }


//                switch (spinnerPlano.get) {
//                    case 1:
//                        if (salBruto <= 3000) {
//                            ps = 60.0;
//                        } else {
//                            ps = 80.0;
//                        }
//                        break;
//                    case 2:
//                        if (salBruto <= 3000) {
//                            ps = 80.0;
//                        } else {
//                            ps = 110.0;
//                        }
//                        break;
//                    case 3:
//                        if (salBruto <= 3000) {
//                            ps = 95.0;
//                        } else {
//                            ps = 135.0;
//                        }
//                        break;
//                    default:
//                        if (salBruto <= 3000) {
//                            ps = 130.0;
//                        } else {
//                            ps = 180.0;
//                        }
//                        break;
//                }
//                return ps;
//            }


                });
            }
        }
