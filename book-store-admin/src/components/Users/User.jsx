const User = (minUser) => {
  minUser = minUser.minUser;
  return (
    <div className="profile-card">
      <p className="profile-id">uid: {minUser.id}</p>
      <p className="profile-email">email: {minUser.email}</p>
      <p className="profile-first-name">first name: {minUser.firstName}</p>
      <p className="profile-last-name">last name: {minUser.lastName}</p>
      <p className="profile-role">role: {minUser.role}</p>
    </div>
  );
};
export default User;
