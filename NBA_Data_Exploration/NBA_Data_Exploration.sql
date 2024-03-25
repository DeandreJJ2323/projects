/* The following queries are grouped into 2 catagories. The first group will contain descriptive data which include data from the past 2 seasson which are 2021-2022 & 2022-2023  and the second group will contain historic descriptive data */

/* Current Descriptive data */

/* Query that gets the team name average points, rebounds, assist's, steals, and turnovers that leads to a win for each team in the 2022-2023 season */
select team_name_home, avg(pts_home), avg(reb_home), avg(ast_home), avg(stl_home), avg(tov_home)
from team_season_stats
where wl_home = "W" && season_id in(42022)
group by team_name_home;

 /* This query gets the home team id, date, team name, & home points where the home team scored more pts than the average with a win in the 2022-2023 season */
 select team_id_home, game_date, team_name_home, pts_home
 from team_season_stats 
 where pts_home > (select avg(pts_home)
 from team_season_stats) && season_id in(42022) 
 order by pts_home;

/* This is a query that shows the name of a team and its average arena attendance over the course of the 2023-2022 season */
 select DISTINCT(team_name_home), Avg(attendance) as avg_attendence
from team_season_stats INNER JOIN game_attendace on team_season_stats.game_id = game_attendace.game_id
where season_id in(42022) && season_type in("Playoffs")
GROUP BY team_name_home;


/* Historic Descriptive data */

/* Query that shows all teams and the number of home wins in descending order*/
select team_id_home ,team_name_home, count(wl_home) as tot_home_wins
from team_season_stats 
where wl_home = "W"
group by team_id_home, team_name_home
order by tot_home_wins desc;

/* Query that shows the most points each team scored while in the playofs */
 select team_id_home, team_abbreviation_home, max(pts_home)
 from team_season_stats
 where season_type in("Playoffs")
 group by team_id_home,team_abbreviation_home;
 
 /* This query finds the date, matchup, and attendace of all matchups since the Aug 5th, 1976 */
 select game_attendace.game_date, team_season_stats.matchup_home, attendance
 from team_season_stats inner join game_attendace on team_season_stats.game_id = game_attendace.game_id;
 
 

 
 
 








