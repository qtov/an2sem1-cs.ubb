using System;
using System.Collections.Generic;
using System.Linq;

namespace lab7.Domain
{
    public class ProjectValidator : IValidator<Project>
    {
        public void Validate(Project pr)
        {
            var errorMsg = new List<string>();

            if (pr.Id < 0)
                errorMsg.Add("The id is invalid.");

            if (pr.Desc.Length < 2)
                errorMsg.Add("Description should contain at least 2 characters.");

            if (pr. Week > 14 || pr.Week < 1)
                errorMsg.Add("Week should be between 1 and 14 (inclusive).");

            if (errorMsg.Any())
                throw new ValidationException(String.Join("\n", errorMsg));
        }
    }
}