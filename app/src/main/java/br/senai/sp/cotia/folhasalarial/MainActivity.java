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
    private TextView TvsalBruto, TvDependentes, TvPlanSaude, TvVTransp, TvVRef, TvVAlim, TvResultSB, TvResultPS, TvResultVT,
            TvResultVR, TvResultVA, TvResultINSS, TvResultIRRF, TvResultSL;
    private EditText editSalBruto, editDependentes;
    private Spinner spinnerPlano;
    private RadioGroup rgVTransp, rgVRef, rgVAlim;
    //    private RadioButton rbSimVT, rbSimVR, rbsimVA, rbNaoVT, rbNaoVR, rbNaoVA;
    private Button btCalcular, btLimpar;

//    private static double salBruto;

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

        //TextViews dos resultados
        TvResultSB = findViewById(R.id.tv_result_sb);
        TvResultPS = findViewById(R.id.tv_result_ps);
        TvResultVT = findViewById(R.id.tv_result_vt);
        TvResultVR = findViewById(R.id.tv_result_vr);
        TvResultVA = findViewById(R.id.tv_result_va);
        TvResultINSS = findViewById(R.id.tv_result_inss);
        TvResultIRRF = findViewById(R.id.tv_result_irrf);
        TvResultSL = findViewById(R.id.tv_result_sl);

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
            } else {

                double sb, sl = 0, desc_ps, desc_inss, desc_transp, desc_aliment, desc_ref,
                        base_IR, desc_IR;
                int numDep;
                numDep = Integer.parseInt(editDependentes.getText().toString());
                sb = Double.parseDouble(editSalBruto.getText().toString());

                // calculando o desconto do plano de saúde
                switch (spinnerPlano.getSelectedItemPosition()) {
                    case 0:
                        if (sb <= 3000) {
                            desc_ps = 60.0;
                        } else {
                            desc_ps = 80.0;
                        }
                        break;
                    case 1:
                        if (sb <= 3000) {
                            desc_ps = 80.0;
                        } else {
                            desc_ps = 110.0;
                        }
                        break;
                    case 2:
                        if (sb <= 3000) {
                            desc_ps = 95.0;
                        } else {
                            desc_ps = 135.0;
                        }
                        break;
                    default:
                        if (sb <= 3000) {
                            desc_ps = 130.0;
                        } else {
                            desc_ps = 180.0;
                        }
                        break;
                }
                // CALCULO DO INSS
                if (sb <= 1212) {
                    desc_inss = sb * 0.075;
                } else if (sb <= 2427.35) {
                    desc_inss = sb * 0.09 - 18.18;
                } else if (sb <= 3641.03) {
                    desc_inss = sb * 0.12 - 91;
                } else if (sb <= 7087.22) {
                    desc_inss = sb * 0.14 - 163.82;
                } else {
                    desc_inss = 7087.22 * 0.14 - 163.82;
                }

                // calculo vale transporte
                if (rgVTransp.getCheckedRadioButtonId() == R.id.rb_simVT) {
                    desc_transp = sb * 0.06;
                } else {
                    desc_transp = 0;
                }

                // calculo vale alimentação
                if (rgVAlim.getCheckedRadioButtonId() == R.id.rb_simVA) {
                    if (sb <= 3000) {
                        desc_aliment = 15.00;
                    } else if (sb <= 5000) {
                        desc_aliment = 25.00;
                    } else {
                        desc_aliment = 35.00;
                    }
                } else {
                    desc_aliment = 0;
                }

                if (rgVRef.getCheckedRadioButtonId() == R.id.rb_simVR) {
                    if (sb <= 3000) {
                        desc_ref = 2.60 * 22;
                    } else if (sb <= 5000) {
                        desc_ref = 3.65 * 22;
                    } else {
                        desc_ref = 6.50 * 22;
                    }
                } else {
                    desc_ref = 0;
                }


                // calculo desconto do imposto de renda (IRRF)
                base_IR = sb - desc_inss - 189.60 * numDep;

                if (base_IR <= 1903.98) {
                    desc_IR = 0;
                } else if (base_IR <= 2826.65) {
                    desc_IR = base_IR * 0.075 - 142.80;
                } else if (base_IR <= 3751.05) {
                    desc_IR = base_IR * 0.15 - 354.80;
                } else if (base_IR <= 4664.68) {
                    desc_IR = base_IR * 0.225 - 636.13;
                } else {
                    desc_IR = base_IR * 0.275 - 869.36;
                }

                sl = sb - desc_inss - desc_transp - desc_ref - desc_aliment - desc_IR - desc_ps;

                // exibindo os resultados na tela
                TvResultSB.setText(getString(R.string.result_sb, sb));
                TvResultPS.setText(getString(R.string.result_ps, desc_ps));
                TvResultVT.setText(getString(R.string.result_vt, desc_transp));
                TvResultVR.setText(getString(R.string.result_vr, desc_ref));
                TvResultVA.setText(getString(R.string.result_va, desc_aliment));
                TvResultINSS.setText(getString(R.string.result_inss, desc_inss));
                TvResultIRRF.setText(getString(R.string.result_irrf, desc_IR));
                TvResultSL.setText(getString(R.string.result_sl, sl));

            }
        });

        btLimpar.setOnClickListener(v -> {
        limparCampos();
        });
    }
    public void limparCampos(){
        editSalBruto.setText("");
        editDependentes.setText("");
//        spinnerPlano.setse
        rgVTransp.clearCheck();
        rgVRef.clearCheck();
        rgVAlim.clearCheck();
        TvResultSB.setText("");
        TvResultPS.setText("");
        TvResultVT.setText("");
        TvResultVR.setText("");
        TvResultVA.setText("");
        TvResultINSS.setText("");
        TvResultIRRF.setText("");
        TvResultSL.setText("");
    }
}
