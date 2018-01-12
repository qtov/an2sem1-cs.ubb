using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;

namespace lab7.Domain
{
    public class StudentValidator : IValidator<Student>
    {
        private bool ValidateEmail(string email) {
            Regex p = new Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
            Match m = p.Match(email);

            return m.Success;
        }
        
        private bool ValidateName(string name)
        {
            var p = new Regex("^[a-zA-Z]+ [a-zA-Z]+[- ]?[a-zA-Z]+$");
            Match m = p.Match(name);

            return m.Success;
        }
        
        public void Validate(Student st)
        {
            var errorMsg = new List<string>();
            
            if (st.Id <= 0)
                errorMsg.Add("The id is invalid.");

            if (!ValidateName(st.Name))
                errorMsg.Add("The name is invalid.");

            if (st.Group.Length != 3)
                errorMsg.Add("The group is invalid.");

            if (!ValidateEmail(st.Email))
                errorMsg.Add("The email is invalid.");

            if (!ValidateName(st.Guide))
                errorMsg.Add("The guide's name is invalid.");

            if (errorMsg.Any())
                throw new ValidationException(String.Join("\n", errorMsg.ToArray()));
        }
    }
}