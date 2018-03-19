module HardConstraints where

forced = replicate 8 -1
forbidden = replicate 8 -1
tooNearTaskSection = replicate 8 -1
-- matrix = replicate
-------------------------------------------------------------------------
outputError outFileName (forcedDoubles forcedPairs)

-- Hard Constraints in main
--let matches = "xxxxxxxx"
--    forcedMatches = doForced forced forbid tooNear matches
--outputError outFileName (forcedMatchValid forcedMatches)
--print ("Forced matches: " ++ forcedMatches)
-------------------------------------------------------------------------

---------------------- CHECKS IF FORBIDDEN MACHINE ----------------------
--isInvalidPair :: Int -> Int -> Bool
--isInvalidPair cm ctn =

-- Boolean. check if the pair is forbidden.
isForbid :: (Char,Char) -> [(Char,Char)] -> Bool
isForbid x forbidden
    | x 'elem' forbidden = True
    | otherwise = False

---------------------- CHECKS IF FORCED PARTIAL -------------------------
--checks if 2 machines assigned same task or vice versa
forcedDouble :: [(Char,Char)] -> String
forcedDouble [] = ""
forcedDouble (x:xs)
    | machine1 'elem' mach2 = "partial assignment error"
    | task1 'elem' task2 = "partial assignment error"
    | otherwise = forcedDouble xs
    where machine1 = fst x
          task1 = snd x
          machine2 = getNextMach xs
          task2 ++ getNextTask xs

getNextMach :: [(Char,Char)] -> [Char]
getNextMach [] = []
getNextMach (x:xs) =
    let machine = fst x
    in [machine] ++ getNextMach xs

getNextTask :: [(Char,Char)] -> [Char]
getNextTask [] = []
getNextTask (x:xs) =
    let task = snd x
    in [task] ++ getNextTask xs

-- Make forced pairs
doForced :: [(Char,Char)] -> [(Char,Char)] -> [(Char,Char)] -> [Char] -> [Char]
doForced [] _ _ matches = matches
doForced (x:xs) forbidden tooNear matches
    | isForbid x forbidden = "No valid solution possible!"
    | otherwise = doForced xs newForbidden tooNear newMatches
    where newMatches = pairUp x matches
          newForbidden = forbidden ++ (isTooNear tooNear (fst x) (snd x))

---------------------- CHECKS IF TOO NEAR -------------------------
isTooNear :: [(Char,Char)] -> Char -> Char -> [(Char,Char)]
isTooNear [] _ _ = []
isTooNear (x:xs) task machine
    | task = taskL [(machineR,taskR)] ++ (isTooNear xs task machine)
    | task = taskR [(machineL,taskL)] ++ (isTooNear xs task machine)
    where taskL = fst x
          taskR = snd x
          machineL = getMachineL machine
          machineR = getMachineR machine

-- Get next machine for too-near
getMachineL :: Char -> Char
getMachineL machine
    | machine == '1'  ='8'
    | otherwise = head newMachine
    where machineInd = (read [machine]::Int) - 1
        newMachine = show machineInd

getMachineR :: Char -> Char
getMachineR machine
    | machine == '8' = '1'
    | otherwise   = head newMachine
    where machineInd = (read [machine]::Int) - 1
        newMachine = show machineInd

--------------------------- MAKES PAIRS ----------------------------
-- Makes final pair
makePair :: (Char,Char) -> [Char] -> [Char]
makePair x matches =
    let machine = fst x     -- turn num string into int index, eg. "1" -> 0
        task = snd x
        index = (read [mach]::Int) - 1
        (as,bs) = splitAt index matches
    in as ++ [task] ++ (tail bs)
