% Create Grid World Environment
env = GridWorld;
env.ResetFcn = @() 2;
rng(0)

% TODO:Create Q-Learning Agent
qTable = rlTable(getObservationInfo(env),getActionInfo(env));
qFunction = rlQValueFunction(qTable,getObservationInfo(env),getActionInfo(env));
qOptions = rlOptimizerOptions("LearnRate",0.01);
agentOpts = rlQAgentOptions;
agentOpts.EpsilonGreedyExploration.Epsilon = .04;
agentOpts.CriticOptimizerOptions = qOptions;
qAgent = rlQAgent(qFunction,agentOpts);

% TODO:Train Q-Learning Agent
trainOpts = rlTrainingOptions;
trainOpts.MaxStepsPerEpisode = 50;
trainOpts.MaxEpisodes= 200;
trainOpts.StopTrainingCriteria = "AverageReward";
trainOpts.StopTrainingValue = 11;
trainOpts.ScoreAveragingWindowLength = 30;

% TODO:Train the agent.
trainingStats = train(qAgent,env,trainOpts);

% TODO:Validate Q-Learning Results
plot(env)
env.Model.Viewer.ShowTrace = true;
env.Model.Viewer.clearTrace;
sim(qAgent,env)