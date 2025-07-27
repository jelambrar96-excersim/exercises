public class SalaryCalculator {

    public double salaryMultiplier(int daysSkipped) {
        return daysSkipped > 4 ? 0.85 : 1.00;        
    }

    public int bonusMultiplier(int productsSold) {
        return productsSold < 20 ? 10 : 13;
    }

    public double bonusForProductsSold(int productsSold) {
        return this.bonusMultiplier(productsSold) * productsSold;
    }

    public double finalSalary(int daysSkipped, int productsSold) {
        double salary = 1000 * this.salaryMultiplier(daysSkipped);
        salary += this.bonusForProductsSold(productsSold);
        return salary > 2000 ? 2000 : salary;
    } 
}
