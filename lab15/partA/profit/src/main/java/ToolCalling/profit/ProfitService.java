package ToolCalling.profit;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfitService {
    @Autowired
    private ProfitRepository profitRepository;

    @Tool(name = "get_profit_by_month", description = "Get the profit data from at specific month")
    public ProfitEntity getProfitByMonth(String month) {
        return profitRepository.findByMonth(month).get();
    }
}
