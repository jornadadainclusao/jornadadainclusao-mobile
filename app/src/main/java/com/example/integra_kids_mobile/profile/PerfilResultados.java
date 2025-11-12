package com.example.integra_kids_mobile.profile;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.integra_kids_mobile.R;
import com.example.integra_kids_mobile.adapter.HistoricoAdapter;
import com.example.integra_kids_mobile.common.ReturnButton;
import com.example.integra_kids_mobile.model.Partida;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerfilResultados extends AppCompatActivity {

    LinearLayout layoutResult;
    Button btnChooseChild, btnChooseGraph;
    TextView textGraph;

    int currentChartIndex = 0; // 0 = Bar, 1 = Pie, 2 = Radar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil_resultados);

        ReturnButton.configurar(this);
        layoutResult = findViewById(R.id.layoutResult);
        btnChooseChild = findViewById(R.id.btnChooseChild);
        btnChooseGraph = findViewById(R.id.btnChooseGraph);
        textGraph = findViewById(R.id.textGraph);

        BarChart barChart = findViewById(R.id.barChart);
        PieChart pieChart = findViewById(R.id.pieChart);
        RadarChart radarChart = findViewById(R.id.radarChart);

        layoutResult.setVisibility(GONE);
        btnChooseGraph.setEnabled(false);

        // Lista simulada de partidas
        List<Partida> lista = new ArrayList<>();
        lista.add(new Partida("Jogo da Memória", "10/11/2025", 8, 2, 45));
        lista.add(new Partida("Jogo das Cores", "09/11/2025", 6, 4, 60));
        lista.add(new Partida("Jogo das Vogais", "08/11/2025", 9, 1, 40));
        lista.add(new Partida("Jogo dos Números", "08/11/2025", 7, 3, 55));
        lista.add(new Partida("Jogo da Memória", "07/11/2025", 10, 0, 38));
        lista.add(new Partida("Jogo das Cores", "07/11/2025", 5, 5, 70));
        lista.add(new Partida("Jogo das Vogais", "06/11/2025", 8, 2, 42));
        lista.add(new Partida("Jogo dos Números", "06/11/2025", 6, 4, 64));

        // ✅ Preparar dados do último resultado de cada jogo
        Map<String, Partida> ultimoPorJogo = new HashMap<>();
        for (Partida p : lista) {
            ultimoPorJogo.put(p.getNomeJogo(), p); // substitui se já existe (mantém o mais recente)
        }

        // ========= Alimentar gráficos =========
        configurarBarChart(barChart, ultimoPorJogo);
        configurarPieChart(pieChart, ultimoPorJogo);
        configurarRadarChart(radarChart, ultimoPorJogo);

        // ========= Botões =========
        btnChooseChild.setOnClickListener(v -> {
            layoutResult.setVisibility(VISIBLE);
            btnChooseGraph.setBackgroundColor(ContextCompat.getColor(this, R.color.tint));
            btnChooseChild.setText("Mudar criança");
            btnChooseGraph.setEnabled(true);
        });

        barChart.setVisibility(VISIBLE);
        pieChart.setVisibility(GONE);
        radarChart.setVisibility(GONE);
        btnChooseGraph.setText("Acertos e erros");
        textGraph.setText("Gráfico de Tentativas:");

        btnChooseGraph.setOnClickListener(v -> {
            currentChartIndex = (currentChartIndex + 1) % 3;

            switch (currentChartIndex) {
                case 0:
                    barChart.setVisibility(VISIBLE);
                    pieChart.setVisibility(GONE);
                    radarChart.setVisibility(GONE);
                    btnChooseGraph.setText("Acertos e erros");
                    textGraph.setText("Gráfico de Tentativas:");
                    break;
                case 1:
                    barChart.setVisibility(GONE);
                    pieChart.setVisibility(VISIBLE);
                    radarChart.setVisibility(GONE);
                    btnChooseGraph.setText("Tempo médio");
                    textGraph.setText("Gráfico de Tempo:");
                    break;
                case 2:
                    barChart.setVisibility(GONE);
                    pieChart.setVisibility(GONE);
                    radarChart.setVisibility(VISIBLE);
                    btnChooseGraph.setText("Desempenho geral");
                    textGraph.setText("Gráfico de Desempenho:");
                    break;
            }
        });

        // RecyclerView de histórico
        RecyclerView recyclerHistorico = findViewById(R.id.recyclerHistorico);
        recyclerHistorico.setLayoutManager(new LinearLayoutManager(this));

        HistoricoAdapter adapter = new HistoricoAdapter(lista, partida -> {
            Toast.makeText(this,
                    "Partida deletada: " + partida.getNomeJogo(), Toast.LENGTH_SHORT).show();
        });
        recyclerHistorico.setAdapter(adapter);
    }

    private void configurarBarChart(BarChart chart, Map<String, Partida> dados) {
        List<BarEntry> tentativas = new ArrayList<>();
        List<BarEntry> acertos = new ArrayList<>();
        List<BarEntry> erros = new ArrayList<>();
        List<String> nomesLimpos = new ArrayList<>();
        List<String> nomesOriginais = new ArrayList<>(dados.keySet());

        // 🔤 Remove o prefixo "Jogo de / da / do / das / dos"
        for (String nomeOriginal : nomesOriginais) {
            String nomeLimpo = nomeOriginal
                    .replaceFirst("(?i)^jogo\\s+(de|da|do|das|dos)\\s+", "")
                    .trim();
            nomesLimpos.add(nomeLimpo);
        }

        for (int i = 0; i < nomesOriginais.size(); i++) {
            Partida p = dados.get(nomesOriginais.get(i));
            tentativas.add(new BarEntry(i, p.getAcertos() + p.getErros()));
            acertos.add(new BarEntry(i, p.getAcertos()));
            erros.add(new BarEntry(i, p.getErros()));
        }

        BarDataSet dsTentativas = new BarDataSet(tentativas, "Tentativas");
        dsTentativas.setColor(Color.rgb(66, 135, 245));
        BarDataSet dsAcertos = new BarDataSet(acertos, "Acertos");
        dsAcertos.setColor(Color.rgb(76, 175, 80));
        BarDataSet dsErros = new BarDataSet(erros, "Erros");
        dsErros.setColor(Color.rgb(244, 67, 54));

        BarData data = new BarData(dsTentativas, dsAcertos, dsErros);
        chart.setData(data);
        chart.getDescription().setEnabled(false);

        int textColor = ContextCompat.getColor(this, R.color.text);

        // 🟢 Cor dos textos
        chart.getXAxis().setTextColor(textColor);
        chart.getAxisLeft().setTextColor(textColor);
        chart.getAxisRight().setTextColor(textColor);
        chart.getLegend().setTextColor(textColor);
        chart.getData().setValueTextColor(textColor);

        // 🟢 Configuração do eixo X
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true); // 🔥 Corrige o desalinhamento
        xAxis.setDrawGridLines(false);
        xAxis.setYOffset(10f);
        xAxis.setTextSize(9f);

        // Formatter com nomes limpos
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int index = (int) value;
                return (index >= 0 && index < nomesLimpos.size()) ? nomesLimpos.get(index) : "";
            }
        });

        // 🟢 Espaçamento e alinhamento corrigido
        float groupSpace = 0.3f;
        float barSpace = 0.05f;
        float barWidth = 0.2f;
        data.setBarWidth(barWidth);

        float groupWidth = data.getGroupWidth(groupSpace, barSpace);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + groupWidth * nomesLimpos.size());

        chart.groupBars(0, groupSpace, barSpace); // agrupa corretamente
        chart.setFitBars(true);
        chart.invalidate();
    }


    // ===================== GRÁFICO DE PIZZA =====================
    private void configurarPieChart(PieChart chart, Map<String, Partida> dados) {
        List<PieEntry> entries = new ArrayList<>();

        for (String nome : dados.keySet()) {
            Partida p = dados.get(nome);
            String nomeCurto = nome.replace("Jogo da ", "").replace("Jogo dos ", "").replace("Jogo das ", "");
            entries.add(new PieEntry(p.getTempo(), nomeCurto));
        }


        PieDataSet dataSet = new PieDataSet(entries, "Tempo (segundos)");
        dataSet.setColors(Color.rgb(66, 135, 245), Color.rgb(76, 175, 80),
                Color.rgb(255, 193, 7), Color.rgb(244, 67, 54));

        PieData data = new PieData(dataSet);
        chart.setData(data);
        chart.setUsePercentValues(false);
        chart.getDescription().setEnabled(false);

        int textColor = ContextCompat.getColor(this, R.color.text);
        int bgColor = ContextCompat.getColor(this, R.color.background); // 🟢

        // 🟢 Centralizar e colorir o meio
        chart.setHoleColor(bgColor);
        chart.setTransparentCircleColor(bgColor);

        chart.getLegend().setTextColor(textColor);
        chart.getData().setValueTextColor(textColor);
        chart.setEntryLabelColor(textColor);

        chart.invalidate();
    }

    private void configurarRadarChart(RadarChart chart, Map<String, Partida> dados) {
        List<RadarEntry> entries = new ArrayList<>();
        List<String> nomes = new ArrayList<>();

        // 🔤 Remove o prefixo "Jogo de / da / do / das / dos"
        for (String nomeOriginal : dados.keySet()) {
            String nomeLimpo = nomeOriginal
                    .replaceFirst("(?i)^jogo\\s+(de|da|do|das|dos)\\s+", "")
                    .trim();
            nomes.add(nomeLimpo);
            entries.add(new RadarEntry(dados.get(nomeOriginal).getAcertos()));
        }

        RadarDataSet dataSet = new RadarDataSet(entries, "Acertos por jogo");
        dataSet.setColor(Color.rgb(33, 150, 243));
        dataSet.setFillColor(Color.rgb(33, 150, 243));
        dataSet.setDrawFilled(true);
        dataSet.setLineWidth(2f);

        RadarData data = new RadarData(dataSet);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getYAxis().setAxisMinimum(0);

        int textColor = ContextCompat.getColor(this, R.color.text);

        chart.getXAxis().setTextColor(textColor);
        chart.getYAxis().setTextColor(textColor);
        chart.getData().setValueTextColor(textColor);

        chart.getXAxis().setTextSize(8f);
        chart.getYAxis().setTextSize(7f);
        chart.getData().setValueTextSize(8f);

        chart.getXAxis().setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                int index = (int) value % nomes.size();
                return (index >= 0 && index < nomes.size()) ? nomes.get(index) : "";
            }
        });

        chart.setExtraOffsets(0, 0, 0, 0);
        chart.setRotationEnabled(false);
        chart.setMinOffset(5f); // dá um leve respiro, mas mantém compacto

        chart.getLegend().setEnabled(false);
        chart.invalidate();
    }


}
