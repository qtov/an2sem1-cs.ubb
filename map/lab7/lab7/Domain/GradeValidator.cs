using System;
using System.Collections.Generic;
using System.Linq;

namespace lab7.Domain
{
    public class GradeValidator : IValidator<Grade>
    {
        public void Validate(Grade gr)
        {
            var errorMsg = new List<string>();
            if (gr.StId < 1) {
                errorMsg.Add("The id of the student is invalid/not found.");
            }

            if (gr.PrId < 1) {
                errorMsg.Add("The id of the project is invalid/not found.");
            }

            if (gr.Value > 10 || gr.Value < 1) {
                errorMsg.Add("Value isn't between 1 and 10.");
            }

            if (gr.InWeek > 14 || gr.InWeek < 1) {
                errorMsg.Add("Week isn't between 1 and 14.");
            }

            if (errorMsg.Any()) {
                throw new ValidationException(String.Join("\n", errorMsg));
            }
        }
    }
}