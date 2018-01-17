using lab7.Repository;

namespace lab7.Domain
{
    public class Grade : IHasId<string>
    {
        private string _id;
        private int _stId;
        private int _prId;
        private float _value;
        private int _inWeek;
        private string _obs;
        
        public string GetId()
        {
            return this._id;
        }

        public void SetId(string idP)
        {
            _id = idP;
        }

        public Grade(int stId, int prId, float value, int inWeek, string obs)
        {
            _id = "" + stId + " " + prId;
            _stId = stId;
            _prId = prId;
            _value = value;
            _inWeek = inWeek;
            _obs = obs;
        }

        public string Id
        {
            get { return _id; }
            set { _id = value; }
        }

        public int StId
        {
            get { return _stId; }
            set { _stId = value; }
        }

        public int PrId
        {
            get { return _prId; }
            set { _prId = value; }
        }

        public float Value
        {
            get { return _value; }
            set { _value = value; }
        }

        public int InWeek
        {
            get { return _inWeek; }
            set { _inWeek = value; }
        }
        
        public string Obs
        {
            get { return _obs; }
            set { _obs = value; }
        }
        
        public override string ToString()
        {
            return "Student id: " + _stId + "\nProject id: " + _prId + "\nValue: " + _value + "\nGiven in week: " + _inWeek + "\nObservations: " + _obs + "\n";
        }
    }
}