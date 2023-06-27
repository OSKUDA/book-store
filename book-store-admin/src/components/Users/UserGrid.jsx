import User from "./User";

const UserGrid = ({ minUserList }) => {
  return (
    <div className="user-container">
      <div className="user-grid">
        {minUserList.map((minUser) => (
          <User key={minUser.id} minUser={minUser} />
        ))}
      </div>
    </div>
  );
};
export default UserGrid;
